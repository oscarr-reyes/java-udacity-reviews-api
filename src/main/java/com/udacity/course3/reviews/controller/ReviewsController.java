package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.document.Review;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import com.udacity.course3.reviews.service.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {
    private final ProductsRepository productsRepository;
    private final ReviewsService reviewsService;

    public ReviewsController(ProductsRepository productsRepository, ReviewsService reviewsService) {
        this.productsRepository = productsRepository;
        this.reviewsService = reviewsService;
    }

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<Review> createReviewForProduct(@PathVariable("productId") Integer productId, @RequestBody Review review) {
        Optional<Product> productRecord = this.productsRepository.findById(new Long(productId));

        if(!productRecord.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            review.setProductId(new Long(productId));

            Review newReview = this.reviewsService.save(review);

            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        List<Review> reviews = this.reviewsService.findAll(new Long(productId));

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}

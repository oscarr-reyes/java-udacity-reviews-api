package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ProductsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {
    private final ReviewsRepository reviewsRepository;
    private final ProductsRepository productsRepository;

    public ReviewsController(ReviewsRepository reviewsRepository, ProductsRepository productsRepository) {
        this.reviewsRepository = reviewsRepository;
        this.productsRepository = productsRepository;
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
            review.setProduct(productRecord.get());

            Review newReview = this.reviewsRepository.save(review);

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
        Product product = new Product();

        product.setId(new Long(productId));

        List<Review> reviews = this.reviewsRepository.findAllByProduct(product);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}

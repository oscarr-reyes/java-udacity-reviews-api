package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.document.ReviewDocument;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.ReviewsMongoRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// TODO: Create unit test for this service
@Service
public class ReviewsService {
	private ReviewsMongoRepository reviewsMongoRepository;
	private ReviewsRepository reviewsRepository;

	public ReviewsService(ReviewsMongoRepository reviewsMongoRepository, ReviewsRepository reviewsRepository) {
		this.reviewsMongoRepository = reviewsMongoRepository;
		this.reviewsRepository = reviewsRepository;
	}

	/**
	 * Retrieves a list of reviews by Product id
	 *
	 * @param productId The id of the product
	 * @return The list of reviews with comments if any exists
	 */
	public List<ReviewDocument> findAll(Long productId) {
		Product product = new Product();

		product.setId(productId);

		List<Review> reviews = this.reviewsRepository.findAllByProduct(product);

		return reviews.stream()
			.map(review -> this.reviewsMongoRepository.findByMysqlId(review.getId()))
			.collect(Collectors.toList());
	}

	public Review save(Review review) {
		ReviewDocument reviewDocument = new ReviewDocument(review.getProduct().getId(), review.getText());
		Review newReview = this.reviewsRepository.save(review);

		reviewDocument.setMysqlId(newReview.getId());

		this.reviewsMongoRepository.save(reviewDocument);

		return newReview;
	}
}

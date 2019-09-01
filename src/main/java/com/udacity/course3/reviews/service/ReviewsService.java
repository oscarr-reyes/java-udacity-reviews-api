package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.document.Review;
import com.udacity.course3.reviews.repository.ReviewsMongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// TODO: Create unit test for this service
@Service
public class ReviewsService {
	private ReviewsMongoRepository reviewsMongoRepository;

	public ReviewsService(ReviewsMongoRepository reviewsMongoRepository) {
		this.reviewsMongoRepository = reviewsMongoRepository;
	}

	/**
	 * Retrieves a list of all reviews
	 * @return The list of reviews with comments if any exists
	 */
	public List<Review> findAll() {
		return this.reviewsMongoRepository.findAll();
	}

	/**
	 * Retrieves a list of reviews by Product id
	 *
	 * @param productId The id of the product
	 * @return The list of reviews with comments if any exists
	 */
	public List<Review> findAll(Long productId) {
		return this.reviewsMongoRepository.findAllByProductId(productId);
	}

	public Review save(Review review) {
		// Make sure that the new review does not have any comment
		review.setComments(new ArrayList<>());

		return this.reviewsMongoRepository.save(review);
	}
}

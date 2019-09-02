package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.document.Comment;
import com.udacity.course3.reviews.document.Review;
import com.udacity.course3.reviews.repository.ReviewsMongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {
	private final ReviewsMongoRepository reviewsMongoRepository;

	public CommentsController(ReviewsMongoRepository reviewsMongoRepository) {
		this.reviewsMongoRepository = reviewsMongoRepository;
	}

	/**
	 * Creates a comment for a review.
	 * <p>
	 * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
	 * 2. Check for existence of review.
	 * 3. If review not found, return NOT_FOUND.
	 * 4. If found, save comment.
	 *
	 * @param reviewId The id of the review.
	 */
	@RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
	public ResponseEntity<Comment> createCommentForReview(@PathVariable("reviewId") String reviewId, @RequestBody Comment comment) {
        Optional<Review> record = this.reviewsMongoRepository.findById(reviewId);

        if (!record.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Review review = record.get();

            review.addComment(comment);

            this.reviewsMongoRepository.save(review);

            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }
	}

	/**
	 * List comments for a review.
	 * <p>
	 * 2. Check for existence of review.
	 * 3. If review not found, return NOT_FOUND.
	 * 4. If found, return list of comments.
	 *
	 * @param reviewId The id of the review.
	 */
	@RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> listCommentsForReview(@PathVariable("reviewId") String reviewId) {
		Optional<Review> record = this.reviewsMongoRepository.findById(reviewId);

        if (!record.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Review review = record.get();
            List<Comment> comments = review.getComments();

            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
	}
}

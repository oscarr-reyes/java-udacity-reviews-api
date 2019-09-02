package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import com.udacity.course3.reviews.service.ReviewsService;
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
	private final ReviewsRepository reviewsRepository;
	private final ReviewsService reviewsService;
	private final CommentsRepository commentsRepository;

	public CommentsController(ReviewsRepository reviewsRepository, ReviewsService reviewsService, CommentsRepository commentsRepository) {
		this.reviewsRepository = reviewsRepository;
		this.reviewsService = reviewsService;
		this.commentsRepository = commentsRepository;
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
		Optional<Review> record = this.reviewsRepository.findById(new Long(reviewId));

		if (!record.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			comment.setReview(record.get());

			Comment newComment = this.reviewsService.saveComment(comment);

			return new ResponseEntity<>(newComment, HttpStatus.CREATED);
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
	public ResponseEntity<List<Comment>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
		Optional<Review> recordReview = this.reviewsRepository.findById(new Long(reviewId));

		if (!recordReview.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			List<Comment> comments = this.commentsRepository.findAllByReview(recordReview.get());

			return new ResponseEntity<>(comments, HttpStatus.OK);
		}
	}
}

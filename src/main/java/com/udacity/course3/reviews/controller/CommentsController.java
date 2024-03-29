package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentsRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final CommentsRepository commentsRepository;

    private final ReviewsRepository reviewsRepository;

    public CommentsController(CommentsRepository commentsRepository, ReviewsRepository reviewsRepository) {
        this.commentsRepository = commentsRepository;
        this.reviewsRepository = reviewsRepository;
    }

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<Comment> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Comment comment) {
        Optional<Review> recordReview = this.reviewsRepository.findById(new Long(reviewId));

        if (!recordReview.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            comment.setReview(recordReview.get());

            Comment newComment = this.commentsRepository.save(comment);

            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        }
    }

    /**
     * List comments for a review.
     *
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

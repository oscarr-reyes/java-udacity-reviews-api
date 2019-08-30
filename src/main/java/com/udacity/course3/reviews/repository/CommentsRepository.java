package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
	public List<Comment> findAllByReview(Review review);
}

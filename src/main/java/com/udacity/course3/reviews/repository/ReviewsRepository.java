package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
	public List<Review> findAllByProduct(Product product);
}

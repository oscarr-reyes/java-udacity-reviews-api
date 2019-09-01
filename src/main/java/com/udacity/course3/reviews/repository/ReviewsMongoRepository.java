package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsMongoRepository extends MongoRepository<Review, String> {
	public List<Review> findAllByProductId(Long id);
}

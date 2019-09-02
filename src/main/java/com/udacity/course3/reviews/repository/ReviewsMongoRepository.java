package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.document.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsMongoRepository extends MongoRepository<ReviewDocument, String> {
	public ReviewDocument findByMysqlId(Long mysqlId);
}

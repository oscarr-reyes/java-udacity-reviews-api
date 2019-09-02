package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.document.ReviewDocument;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewsMongoRepositoryTest {
	@Autowired
	ReviewsMongoRepository reviewsMongoRepository;

	@Test
	public void should_find_reviews_by_mysql_product() {
		ReviewDocument mockReviewDocument = new ReviewDocument(1L, "foo-text");

		mockReviewDocument.setMysqlId(2L);

		this.reviewsMongoRepository.save(mockReviewDocument);

		ReviewDocument review = this.reviewsMongoRepository.findByMysqlId(2L);

		Assert.assertNotNull(review);
	}
}

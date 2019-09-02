package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.document.Review;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewsMongoRepositoryTest {
	@Autowired
	ReviewsMongoRepository reviewsMongoRepository;

	@Test
	public void should_find_reviews_by_product() {
		Review mockReview = new Review(1L, "foo-text");

		this.reviewsMongoRepository.save(mockReview);

		List<Review> reviews = this.reviewsMongoRepository.findAllByProductId(1L);

		Assert.assertThat(reviews, not(empty()));
	}
}

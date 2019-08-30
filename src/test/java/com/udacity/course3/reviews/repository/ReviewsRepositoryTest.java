package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsRepositoryTest {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ReviewsRepository reviewsRepository;

	@Test
	public void should_make_inject_components_work() {
		Assert.assertNotNull(this.entityManager);
		Assert.assertNotNull(this.reviewsRepository);
	}

	@Test
	public void should_find_reviews_by_products() {
		Product mockProduct = new Product("foo", "bar");

		Review mockReview = new Review("qux", mockProduct);

		this.entityManager.persist(mockProduct);
		this.entityManager.persist(mockReview);
		this.entityManager.flush();

		List<Review> reviews = this.reviewsRepository.findAllByProduct(mockProduct);

		Assert.assertThat(reviews, not(empty()));
	}
}

package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comment;
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
public class CommentsRepositoryTest {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private CommentsRepository commentsRepository;


	@Test
	public void should_make_injected_components_work() {
		Assert.assertNotNull(this.entityManager);
		Assert.assertNotNull(this.commentsRepository);
	}

	@Test
	public void should_find_comments_by_review() {
		Review mockReview = this.mockReview();
		Comment mockComment = new Comment("comment-text", mockReview);

		this.entityManager.persist(mockComment);

		List<Comment> comments = this.commentsRepository.findAllByReview(mockReview);

		Assert.assertThat(comments, not(empty()));
	}

	private Review mockReview() {
		Product mockProduct = new Product("product-name", "product-type");

		this.entityManager.persist(mockProduct);
		this.entityManager.flush();

		Review mockReview = new Review("review-text", mockProduct);

		this.entityManager.persist(mockReview);
		this.entityManager.flush();

		return mockReview;
	}
}

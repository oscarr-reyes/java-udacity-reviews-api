package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;
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
public class ProductsRepositoryTest {
	@Autowired private EntityManager entityManager;
	@Autowired private ProductsRepository productsRepository;

	@Test
	public void should_make_injected_components_work() {
		Assert.assertNotNull(this.entityManager);
		Assert.assertNotNull(this.productsRepository);
	}

	@Test
	public void should_find_all_products() {
		Product product = new Product("foo", "bar");

		this.entityManager.persist(product);

		List<Product> products = this.productsRepository.findAll();

		Assert.assertThat(products, not(empty()));
	}
}

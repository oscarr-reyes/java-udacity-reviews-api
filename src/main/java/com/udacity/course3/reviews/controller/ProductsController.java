package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	ProductsRepository productsRepository;

	/**
	 * Creates a product.
	 * <p>
	 * 1. Accept product as argument. Use {@link RequestBody} annotation.
	 * 2. Save product.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product newProduct = this.productsRepository.save(product);

		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}

	/**
	 * Finds a product by id.
	 *
	 * @param id The id of the product.
	 * @return The product if found, or a 404 not found.
	 */
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
		Optional<Product> record = this.productsRepository.findById(new Long(id));

		if (!record.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Product product = record.get();

			return new ResponseEntity<>(product, HttpStatus.FOUND);
		}
	}

	/**
	 * Lists all products.
	 *
	 * @return The list of products.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Product> listProducts() {
		return this.productsRepository.findAll();
	}
}

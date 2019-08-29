package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;

	@Column(name = "products_id")
	private Long productsId;

	public Review() {
	}

	public Review(String text, Long productsId) {
		this.text = text;
		this.productsId = productsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getProductsId() {
		return productsId;
	}

	public void setProductsId(Long productsId) {
		this.productsId = productsId;
	}
}

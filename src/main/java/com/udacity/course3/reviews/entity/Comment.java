package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;

	@Column(name = "reviews_id")
	private Long reviewsId;

	public Comment() {
	}

	public Comment(String text, Long reviewsId) {
		this.text = text;
		this.reviewsId = reviewsId;
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

	public Long getReviewsId() {
		return reviewsId;
	}

	public void setReviewsId(Long reviewsId) {
		this.reviewsId = reviewsId;
	}
}

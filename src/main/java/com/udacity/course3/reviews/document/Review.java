package com.udacity.course3.reviews.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document("reviews")
public class Review {
	@Id
	private String id;

	private String text;
	private List<Comment> comments;
	private Long productId;

	public Review() {
	}

	public Review(Long productId) {
		this.productId = productId;
	}

	public Review(Long productId, String text) {
		this.productId = productId;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment) {
		if (this.comments == null) {
			this.comments = new ArrayList<>();
		}

		this.comments.add(comment);
	}

	@Override
	public String toString() {
		String template = "{id: %s, text: %s, productId: %d}";

		return String.format(template, this.getId(), this.getText(), this.getProductId());
	}
}

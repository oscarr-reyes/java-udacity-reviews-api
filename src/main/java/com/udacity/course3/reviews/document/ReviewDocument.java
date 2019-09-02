package com.udacity.course3.reviews.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document("reviews")
public class ReviewDocument {
	@Id
	private String id;

	private String text;
	private List<CommentDocument> commentDocuments;
	private Long productId;
	private Long mysqlId;

	public ReviewDocument() {
	}

	public ReviewDocument(Long productId) {
		this.productId = productId;
	}

	public ReviewDocument(Long productId, String text) {
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

	public Long getMysqlId() {
		return mysqlId;
	}

	public void setMysqlId(Long mysqlId) {
		this.mysqlId = mysqlId;
	}

	public List<CommentDocument> getCommentDocuments() {
		return commentDocuments;
	}

	public void setCommentDocuments(List<CommentDocument> commentDocuments) {
		this.commentDocuments = commentDocuments;
	}

	public void addComment(CommentDocument commentDocument) {
		if (this.commentDocuments == null) {
			this.commentDocuments = new ArrayList<>();
		}

		this.commentDocuments.add(commentDocument);
	}
}

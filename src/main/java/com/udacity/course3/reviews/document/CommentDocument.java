package com.udacity.course3.reviews.document;

public class CommentDocument {
	private String text;

	public CommentDocument() {
	}

	public CommentDocument(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

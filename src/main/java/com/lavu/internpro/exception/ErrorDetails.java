package com.lavu.internpro.exception;

import java.util.Date;

public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public ErrorDetails(Date timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;
	}
	public ErrorDetails(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}

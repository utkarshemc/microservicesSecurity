package com.dell.user.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final HttpStatus httpStatus;

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public UserException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

}

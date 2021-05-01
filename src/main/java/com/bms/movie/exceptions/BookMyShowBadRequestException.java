package com.bms.movie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookMyShowBadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookMyShowBadRequestException() {
		super();
	}

	public BookMyShowBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookMyShowBadRequestException(String message) {
		super(message);
	}
	
	public BookMyShowBadRequestException(Throwable cause) {
		super(cause);
	}

}

package com.zupedu.gabriel.mercadolivre.resources.exceptions;

public class InvalidArgumentRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidArgumentRequestException(String msg) {
		super(msg);
	}

	public InvalidArgumentRequestException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

package com.luanvv.microservices.product.exceptions;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String exception) {
		super(exception);
	}

}

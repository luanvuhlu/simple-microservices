package com.luanvv.microservices.product.exceptions;

public class DataSourceSecretEmptyOrNullException extends DataSourceSecretInvalidException {

	public DataSourceSecretEmptyOrNullException(String message) {
		super(message);
	}

}

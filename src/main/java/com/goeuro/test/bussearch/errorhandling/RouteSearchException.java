package com.goeuro.test.bussearch.errorhandling;

public class RouteSearchException extends Exception {

	private static final long serialVersionUID = 1L;

	public RouteSearchException(final String message) {
		super(message);
	}
	
	public RouteSearchException(final Throwable exc) {
		super(exc);
	}
}

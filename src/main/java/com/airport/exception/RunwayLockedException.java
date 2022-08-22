package com.airport.exception;

public class RunwayLockedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RunwayLockedException() {
		super();
	}

	public RunwayLockedException(String message) {
		super(message);
	}

}

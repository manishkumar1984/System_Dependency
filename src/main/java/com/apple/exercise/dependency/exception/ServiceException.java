package com.apple.exercise.dependency.exception;

/**
 * @author Manish Kumar (manishkumar1984@gmail.com)
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 2286966138375157486L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

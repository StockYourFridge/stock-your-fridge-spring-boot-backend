package com.stockyourfridge.stockyourfridge.exception;

/**
 * Exception class when user with userName is not found.
 * 
 * @author rhishabh
 *
 */
public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String userName) {
		super("User with userName: " + userName + " not found.");
	}
	
}

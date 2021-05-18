package com.stockyourfridge.stockyourfridge.exception;

public class SubscriberNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubscriberNotFoundException(String userName, long fridgeId) {
		super("User with userName : " + userName + " was not subscribed to fridge with fridgeId : " + fridgeId);
	}
	
}

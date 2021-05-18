package com.stockyourfridge.stockyourfridge.exception;

/**
 * Exception when fridge is not found.
 * 
 * @author rhishabh
 *
 */
public class FridgeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FridgeNotFoundException(Long fridgeId) {
		super("Fridge with fridgeId: " + fridgeId + " not found.");
	}
	
}

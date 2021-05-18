package com.stockyourfridge.stockyourfridge.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * class to handle all exceptions
 * 
 * @author rhishabh
 *
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	// handling fridge not found exception
	@ExceptionHandler(FridgeNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionHandling(FridgeNotFoundException exception, WebRequest request) {
		log.error("FridgeNotFoundException: " + exception.getMessage());
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	// handling user not found exception
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionHandling(UserNotFoundException exception, WebRequest request) {
		log.error("UserNotFoundException: " + exception.getMessage());
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	// handling global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
		log.error("Exception: " + exception.getMessage());
		Date date = new Date();
		ErrorDetails errorDetails = new ErrorDetails(date, exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

package com.stockyourfridge.stockyourfridge.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * the class is used to hold the details of the errors 
 * that will be sent back to the client in case of exceptions and errors
 * 
 * @author rhishabh
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	private Date timeStamp;
	private String message;
	private String details;
}

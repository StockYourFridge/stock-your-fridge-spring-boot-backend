package com.stockyourfridge.stockyourfridge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockyourfridge.stockyourfridge.service.SubscriberService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/subscribers")
public class SubscriberController {

	private final SubscriberService subscriberService;
	
	@PutMapping("/userName/{userName}/fridgeId/{fridgeId}")
	public ResponseEntity<String> subscribeToFridge(@PathVariable String userName, @PathVariable long fridgeId) throws Exception {
		
		subscriberService.subscribeToFridge(userName, fridgeId);
		
		return ResponseEntity.ok("User : " + userName + " successfully subscribed to fridge : " + fridgeId);
	}
	
	@DeleteMapping("/userName/{userName}/fridgeId/{fridgeId}")
	public ResponseEntity<String> unsubscribeFromFridge(@PathVariable String userName, @PathVariable long fridgeId) throws Exception {
		
		subscriberService.unsubscribeFromFridge(userName, fridgeId);
		
		return ResponseEntity.ok("User : " + userName + " successfully unsubscribed from fridge : " + fridgeId);
	}
	
}

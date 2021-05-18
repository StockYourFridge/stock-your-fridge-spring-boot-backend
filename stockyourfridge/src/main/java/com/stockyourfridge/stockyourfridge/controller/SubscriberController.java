package com.stockyourfridge.stockyourfridge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockyourfridge.stockyourfridge.service.FridgeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/subscribers")
public class SubscriberController {

	private final FridgeService fridgeService;
	
	@PutMapping("/userName/{userName}/fridgeId/{fridgeId}")
	public ResponseEntity<String> subscribeToFridge(@PathVariable String userName, @PathVariable long fridgeId) throws Exception {
		
		fridgeService.subscribeToFridge(userName, fridgeId);
		
		return ResponseEntity.ok("User : " + userName + " successfully subscribed to fridge : " + fridgeId);
	}
	
}

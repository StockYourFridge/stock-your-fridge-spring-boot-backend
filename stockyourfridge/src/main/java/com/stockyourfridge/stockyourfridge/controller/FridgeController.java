package com.stockyourfridge.stockyourfridge.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockyourfridge.stockyourfridge.dto.FridgeDto;
import com.stockyourfridge.stockyourfridge.service.FridgeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/fridges")
@AllArgsConstructor
public class FridgeController {

	private final FridgeService fridgeService;
	
	@PostMapping
	public FridgeDto addFridge(@RequestBody FridgeDto fridgeDto) throws Exception {
		return fridgeService.addFridge(fridgeDto);
	}
	
	@GetMapping("/owned/{owner}")
	public List<FridgeDto> getFridgesByOwner(@PathVariable String owner) throws Exception {
		return fridgeService.getFridgesByOwner(owner);
	}
	
	@DeleteMapping("/{fridgeId}")
	public ResponseEntity<String> deleteFridge(@PathVariable long fridgeId) {
		fridgeService.deleteFridge(fridgeId);
		
		return ResponseEntity.ok("Fridge with id : " + fridgeId + " has been deleted successfully.");
	}
	
	@PutMapping
	public FridgeDto updateFridge(@RequestBody FridgeDto fridgeDto) throws Exception {
		return fridgeService.updateFridge(fridgeDto);
	}
	
	//TODO unsubscribe fridge
	//TODO get subscribed fridges
	//TODO get subscribers for fridge
	//TODO get owner for fridge
	//TODO remove subscriber from fridge
	//TODO custom exception
	//TODO unit testing
}

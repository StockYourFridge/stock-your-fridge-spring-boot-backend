package com.stockyourfridge.stockyourfridge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockyourfridge.stockyourfridge.dto.UserDto;
import com.stockyourfridge.stockyourfridge.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping
	public UserDto addUser(@RequestBody @Valid UserDto userDto) {
		return userService.addUser(userDto);
	}
	
	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}
}

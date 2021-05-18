package com.stockyourfridge.stockyourfridge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stockyourfridge.stockyourfridge.dto.UserDto;
import com.stockyourfridge.stockyourfridge.model.User;
import com.stockyourfridge.stockyourfridge.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;

	public UserDto addUser(UserDto userDto) {
		
		User toSaveUser = User.builder()
								.userName(userDto.getUserName())
								.password(userDto.getPassword())
								.email(userDto.getEmail())
								.build();
		
		User user = userRepository.save(toSaveUser);
		
		return UserDto.builder()
						.userId(user.getUserId())
						.userName(user.getUserName())
						.password(user.getPassword())
						.email(user.getEmail())
						.build();
	}

	public List<UserDto> getAllUsers() {
		
		List<User> users = (List<User>) userRepository.findAll();
		List<UserDto> userDtos = mapUsersToUserDtos(users);
		
		log.debug("Mapped users to user dtos : " + userDtos);
		
		return userDtos;
	}
	
	public List<UserDto> mapUsersToUserDtos(List<User> users) {
		List<UserDto> userDtos = new ArrayList<>();
		
		for (User user : users) {
			UserDto userDto = UserDto.builder()
								.userId(user.getUserId())
								.userName(user.getUserName())
								.password(user.getPassword())
								.email(user.getEmail())
								.isEnabled(user.isEnabled())
								.build();
			userDtos.add(userDto);
		}
		
		return userDtos;
	}
	
}

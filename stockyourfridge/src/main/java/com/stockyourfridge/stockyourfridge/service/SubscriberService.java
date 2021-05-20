package com.stockyourfridge.stockyourfridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stockyourfridge.stockyourfridge.dto.UserDto;
import com.stockyourfridge.stockyourfridge.exception.FridgeNotFoundException;
import com.stockyourfridge.stockyourfridge.exception.SubscriberNotFoundException;
import com.stockyourfridge.stockyourfridge.exception.UserNotFoundException;
import com.stockyourfridge.stockyourfridge.model.Fridge;
import com.stockyourfridge.stockyourfridge.model.User;
import com.stockyourfridge.stockyourfridge.repository.FridgeRepository;
import com.stockyourfridge.stockyourfridge.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SubscriberService {

	private final FridgeRepository fridgeRepository;
	private final UserRepository userRepository;
	
	public void subscribeToFridge(String userName, long fridgeId) throws Exception {
		Fridge fridge = fridgeRepository.findById(fridgeId)
				.orElseThrow(() -> new FridgeNotFoundException(fridgeId));
		log.debug("Found fridge");
		
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException(userName));
		log.debug("Found user");
		
		//TODO don't add subscriber if user is owner
		
		List<User> users = fridge.getUsers();
		log.debug("Found users from fridge");
		
		users.add(user);
		log.debug("Added user in users");
		
		fridge.setUsers(users);
		log.debug("Saving in repo");
		
		fridgeRepository.save(fridge);
	}

	public void unsubscribeFromFridge(String userName, long fridgeId) {
		Fridge fridge = fridgeRepository.findById(fridgeId)
				.orElseThrow(() -> new FridgeNotFoundException(fridgeId));
		log.debug("Found fridge");
		
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException(userName));
		log.debug("Found user");
		
		List<User> users = fridge.getUsers();
		log.debug("Found users from fridge");

		if (!users.contains(user)) {
			throw new SubscriberNotFoundException(userName, fridgeId);
		}
		
		users.remove(user);
		log.debug("Removed user from users");
		
		fridgeRepository.save(fridge);
		
	}

	public List<UserDto> getSubscribersOfFridge(long fridgeId) {
		Fridge fridge = fridgeRepository.findById(fridgeId)
							.orElseThrow(() -> new FridgeNotFoundException(fridgeId));
		
		List<User> subscribers = fridge.getUsers();
		
		List<UserDto> subscriberDtos = UserService.mapUsersToUserDtos(subscribers);
		log.debug("Subscribers of fridge with id : " + fridgeId + " : " + subscriberDtos);
		
		return subscriberDtos;
	}
	
}

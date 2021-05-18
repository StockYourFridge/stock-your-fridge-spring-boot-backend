package com.stockyourfridge.stockyourfridge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.stockyourfridge.stockyourfridge.dto.FridgeDto;
import com.stockyourfridge.stockyourfridge.exception.FridgeNotFoundException;
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
public class FridgeService {

	private final FridgeRepository fridgeRepository;
	private final UserRepository userRepository;

	public FridgeDto addFridge(FridgeDto fridgeDto) throws Exception {
		log.debug("Received fridgeDto : " + fridgeDto);

		//TODO use current user as owner
		User owner = userRepository.findByUserName(fridgeDto.getOwner())
				.orElseThrow(() -> new UserNotFoundException(fridgeDto.getOwner()));
		
		Fridge toSaveFridge = Fridge.builder()
									.name(fridgeDto.getName())
									.owner(owner)
									.isFull(false)
									.build();
		Fridge savedFridge = fridgeRepository.save(toSaveFridge);
		
		FridgeDto savedFridgeDto = FridgeDto.builder()
				.fridgeId(savedFridge.getFridgeId())
				.name(savedFridge.getName())
				.owner(savedFridge.getOwner().getUserName())
				.isFull(savedFridge.isFull())
				.build();
		
		log.debug("Saved Fridge Dto : " + savedFridgeDto);
		
		return savedFridgeDto;
	}
	
	private List<FridgeDto> mapFridgesToFridgeDtos(List<Fridge> fridges) {
		List<FridgeDto> fridgeDtos = new ArrayList<>();
		
		for (Fridge fridge : fridges) {
			FridgeDto fridgeDto = FridgeDto.builder()
											.fridgeId(fridge.getFridgeId())
											.name(fridge.getName())
											.owner(fridge.getOwner().getUserName())
											.isFull(fridge.isFull())
											.build();
			fridgeDtos.add(fridgeDto);
		}
		
		return fridgeDtos;
	}


	public List<FridgeDto> getFridgesByOwner(String userName) throws Exception {
		log.debug("Getting fridges for userName : " + userName);
		
		User owner = userRepository.findByUserName(userName)
						.orElseThrow(() -> new UserNotFoundException(userName));
		
		List<Fridge> fridgesByOwner = fridgeRepository.findByOwner(owner);
		List<FridgeDto> mappedFridgesByOwner = mapFridgesToFridgeDtos(fridgesByOwner);
		
		log.debug("Mapped Fridges by owner : " + mappedFridgesByOwner);
		
		return mappedFridgesByOwner;
	}

	public void subscribeToFridge(String userName, long fridgeId) throws Exception {
		Fridge fridge = fridgeRepository.findById(fridgeId)
				.orElseThrow(() -> new FridgeNotFoundException(fridgeId));
		
		log.debug("Found fridge");
		
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException(userName));
		
		log.debug("Found user");
		
		List<User> users = fridge.getUsers();
		
		log.debug("Found users from fridge");
		users.add(user);
		log.debug("Added user in users");
		fridge.setUsers(users);
		log.debug("Saving in repo");
		fridgeRepository.save(fridge);
	}

	public void deleteFridge(long fridgeId) {
		log.debug("Deleting fridge with id : " + fridgeId);
		
		fridgeRepository.deleteById(fridgeId);
	}

	public FridgeDto updateFridge(FridgeDto fridgeDto) throws Exception {
		
		log.debug("Updating fridge with dto : " + fridgeDto);
		
		User owner = userRepository.findByUserName(fridgeDto.getOwner())
						.orElseThrow(() -> new UserNotFoundException(fridgeDto.getOwner()));
		
		// check if fridge exists in db
		fridgeRepository.findById(fridgeDto.getFridgeId())
						.orElseThrow(() -> new FridgeNotFoundException(fridgeDto.getFridgeId()));
		
		Fridge toUpdateFridge = Fridge.builder()
									.fridgeId(fridgeDto.getFridgeId())
									.name(fridgeDto.getName())
									.owner(owner)
									.isFull(fridgeDto.isFull())
									.build();
		
		Fridge updatedFridge = fridgeRepository.save(toUpdateFridge);
		
		FridgeDto savedFridgeDto = FridgeDto.builder()
				.fridgeId(updatedFridge.getFridgeId())
				.name(updatedFridge.getName())
				.owner(updatedFridge.getOwner().getUserName())
				.isFull(updatedFridge.isFull())
				.build();
		
		log.debug("Updated fridge dto : " + savedFridgeDto);
		
		return savedFridgeDto;
	}
}

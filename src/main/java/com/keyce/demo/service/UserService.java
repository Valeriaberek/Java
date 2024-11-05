package com.keyce.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyce.demo.dto.UserDto;
import com.keyce.demo.exception.CreateUserIdException;
import com.keyce.demo.model.UserModel;
import com.keyce.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Collection<UserDto> getAllUsers() {
		return modelsToDtos(userRepository.findAll());
	}

	public UserDto createUser(UserDto dto) throws CreateUserIdException {
		if(dto.getId()!=null) {
			throw new CreateUserIdException("Il est interdit de mettre un Id afin de créer un utilisateur");
		}
		return modelToDto(userRepository.save(dtoToModel(dto)));
	}

	public UserDto updateUser(UserDto dto) {
		return modelToDto(userRepository.save(dtoToModel(dto)));
	}

	private Collection<UserDto> modelsToDtos(Iterable<UserModel> userModels) {
		Collection<UserDto> userDtos = new ArrayList<>();
		userModels.forEach(userModel -> {
			userDtos.add(modelToDto(userModel));
		});
		return userDtos;
	}

	private UserDto modelToDto(UserModel userModel) {
		UserDto userDto = new UserDto();
		userDto.setEmail(userModel.getEmail());
		userDto.setId(userModel.getId());
		userDto.setName(userModel.getName());
		return userDto;
	}

	private UserModel dtoToModel(UserDto userDto) {
		UserModel userModel = new UserModel();
		userModel.setEmail(userDto.getEmail());
		userModel.setId(userDto.getId());
		userModel.setName(userDto.getName());
		return userModel;
	}

	public UserDto getUser(long id) {
		return modelToDto(userRepository.findById(id).get());
	}

	public boolean deleteUser(long id) {
		userRepository.deleteById(id);
		return true;
	}

}

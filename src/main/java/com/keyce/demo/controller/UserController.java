package com.keyce.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyce.demo.dto.UserDto;
import com.keyce.demo.exception.CreateUserIdException;
import com.keyce.demo.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public Collection<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable("id") long id) {
		return userService.getUser(id);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteUser(@PathVariable("id") long id) {
		return userService.deleteUser(id);
	}
	
	@PostMapping()
	public UserDto createUser(@RequestBody UserDto userDto) throws CreateUserIdException {
		return userService.createUser(userDto);
	}
	
	@PutMapping()
	public UserDto updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}
	
	@GetMapping("populate")
	public void populate() throws CreateUserIdException {
		UserDto userDto = new UserDto();
		userDto.setEmail("toto@test.fr");
		userDto.setName("Toto");
		userService.createUser(userDto);
		
	}

}

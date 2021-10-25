package com.zkteco.user.controller;

import java.util.List;
import java.util.Map;

//import javax.validation.Valid;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.user.dto.ResultDTO;
import com.zkteco.user.dto.UserDTO;
import com.zkteco.user.entity.User;
import com.zkteco.user.exception.UserNotFoundException;
import com.zkteco.user.service.UserService;

import io.swagger.annotations.Api;
/**
 * 
 * @author sameerb
 *
 */
@RestController
@Api(value = "user")
@RequestMapping("/api/v1/users")

public class UserController {
	@Autowired
	
	private UserService userService;

	@GetMapping
	public ResultDTO findPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
			//throws UserNotFoundException 
	{
	ResultDTO res = userService.getAllUser(page, size);
	return res;
	}

	@GetMapping("/{id}")
	public ResultDTO fetchUserById(@PathVariable("id")  String userId)throws UserNotFoundException 
	{
		return userService.fetchUserById(userId);
	}

	@PostMapping
	//@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ResultDTO saveUser( @Validated @RequestBody UserDTO dto) throws UserNotFoundException 
	{
		return userService.saveUser(dto);
	}


	@PutMapping("/{id}")
	public ResultDTO updateUser(@PathVariable("id")  String userId, @RequestBody UserDTO user) throws UserNotFoundException {
		return userService.updateUser(userId, user);
	}

	@DeleteMapping
	public ResultDTO deleteUserById(@RequestParam("id") String userId) throws UserNotFoundException {
		return userService.deleteUserById(userId);
	}
// using global exception

	
}

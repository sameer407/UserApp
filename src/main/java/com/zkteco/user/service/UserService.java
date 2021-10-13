package com.zkteco.user.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.zkteco.user.dto.ResultDTO;
import com.zkteco.user.dto.UserDTO;
import com.zkteco.user.entity.User;
import com.zkteco.user.exception.UserNotFoundException;

//import com.dailycodebuffer.Springboottutorial.controller.entity.Department;

public interface UserService {
	//public ResultDTO saveUser(User user);

	

	//public List<User> fetchUserList();
	public ResultDTO fetchUserById(String userId) throws UserNotFoundException ;
		
	public ResultDTO deleteUserById(String userId) throws UserNotFoundException;

	
	
	public UserDTO entityToDto(User usr);
	public List<UserDTO> entityToDto(List<User> usr);
	public List<User> dtoToEntity(List<UserDTO> dto);
	public User dtoToEntity(UserDTO dto);

	ResultDTO updateUser(String userId, UserDTO user) throws UserNotFoundException;



	ResultDTO saveUser(UserDTO dto) throws UserNotFoundException;



	public ResultDTO getAllUser(int page, int size);


	
}

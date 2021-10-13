package com.zkteco.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zkteco.user.count.ErrorCount;
import com.zkteco.user.count.SuccessCount;
import com.zkteco.user.dto.ResultDTO;
import com.zkteco.user.dto.UserDTO;
import com.zkteco.user.entity.User;
import com.zkteco.user.exception.UserNotFoundException;
import com.zkteco.user.repository.UserRepository;
import com.zkteco.user.service.UserService;

@Service
public  class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	public UserDTO entityToDto(User usr) {
		UserDTO dto = new UserDTO();
		dto.setUserId(usr.getUserId());
		dto.setFirstName(usr.getFirstName());
		dto.setLastName(usr.getLastName());
		dto.setAddress(usr.getAddress());
		dto.setEmail_Id(usr.getEmail_Id());
		dto.setUserCode(usr.getUserCode());
		dto.setGender(usr.getGender());

		return dto;
	}

	public List<UserDTO> entityToDto(List<User> usr) {
		return usr.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	public User dtoToEntity(UserDTO dto) {
		User usr = new User();
		usr.setUserId(dto.getUserId());
		usr.setFirstName(dto.getFirstName());
		usr.setLastName(dto.getLastName());
		usr.setAddress(dto.getAddress());
		usr.setEmail_Id(dto.getEmail_Id());
		usr.setUserCode(dto.getUserCode());
		usr.setGender(dto.getGender());
		return usr;
	}

	public List<User> dtoToEntity(List<UserDTO> dto) {// looping happenning for saving in list
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
//to save operation
	
	
	
	@Override
	public ResultDTO saveUser(UserDTO dto) throws UserNotFoundException
	{	
		int size=dto.getFirstName().length();
		if(dto.getFirstName().isEmpty()&&dto.getEmail_Id().isEmpty())
		{
			ResultDTO result = new ResultDTO();
			result.setCode("User-01");
			result.setMessage("first name and address must not be empty");
			return result;
		}
		else if(dto.getFirstName().isEmpty())
		{
		ResultDTO result = new ResultDTO();
		result.setCode("User-01");
		result.setMessage("first name must not be null");
		return result;
		}else if(dto.getEmail_Id().isEmpty())
		{
		ResultDTO result = new ResultDTO();
		result.setCode("User-01");
		result.setMessage("Email should not be null");
		return result;
		}else if(size<3)
		{
			ResultDTO result = new ResultDTO();
			result.setCode("User-01");
			result.setMessage("usert Name must be min 3 characters max 10 characters");
			return result;
			
		}
		
		User user = userService.dtoToEntity(dto);
		user.setCreatedate(new Date());
		user = userRepository.save(user);
		UserDTO usrdto = userService.entityToDto(user);
		ResultDTO result = new ResultDTO();
		result.setCode("User-01");
		result.setMessage("User resource created successfully");
		result.setData(usrdto);
		return result;
	}
	
//To retrive by ID
	@Override
	public ResultDTO fetchUserById(String userId) throws UserNotFoundException {
		
		Optional<User>  orElse= userRepository.findById(userId);
		if (!orElse.isPresent()) { 
			throw new UserNotFoundException("User Not Available"); 
			}
		User user=orElse.get();

		UserDTO dto = userService.entityToDto(user);
		ResultDTO result = new ResultDTO();
		result.setCode("User-01");
		result.setMessage("User Details found");
		result.setData(user);



	return result;
	}

//  to Update by ID
	@Override
	public ResultDTO updateUser(String userId, UserDTO user)throws UserNotFoundException {
		
		Optional<User> usr = userRepository.findById(userId);
		if (!usr.isPresent()) { 
			throw new UserNotFoundException("User Not Available"); 
			}
		
		User useDB = usr.get();
		
		
		if (Objects.nonNull(user.getUserId()) && !"".equals(user.getUserId())) {
			useDB.setUserId(user.getUserId());
		}

		if (Objects.nonNull(user.getFirstName()) && !"".equals(user.getFirstName())) {
			useDB.setFirstName(user.getFirstName());
		}

		if (Objects.nonNull(user.getLastName()) && !"".equals(user.getLastName())) {
			useDB.setLastName(user.getLastName());
		}

		if (Objects.nonNull(user.getAddress()) && !"".equals(user.getAddress())) {
			useDB.setAddress(user.getAddress());
		}

		if (Objects.nonNull(user.getEmail_Id()) && !"".equals(user.getEmail_Id())) {
			useDB.setEmail_Id(user.getEmail_Id());
		}
		if (Objects.nonNull(user.getUserCode()) && !"".equals(user.getUserCode())) {
			useDB.setUserCode(user.getUserCode());
		}
		if (Objects.nonNull(user.getGender()) && !"".equals(user.getGender())) {
			useDB.setGender(user.getGender());
		}
		useDB.setUpdatedate(new Date());
		User usr1 = userRepository.save(useDB);
		UserDTO dto = userService.entityToDto(usr1);

		ResultDTO result = new ResultDTO();
		result.setCode("user-006");
		result.setMessage("successfully updated user details");
		result.setData(usr);
		return result;

	}
//Delete BY Id
	@Override
	public ResultDTO deleteUserById(String userId) throws UserNotFoundException {
		//85,63,52,44
		
		String[] ids=null;
			ids=userId.split(",");
			

		int successCount=0;
		int errorCount=0;
		ResultDTO resdto = new ResultDTO();
		SuccessCount count=new SuccessCount();
		ErrorCount count1 = new ErrorCount();
		List<ErrorCount> lst2=new ArrayList<ErrorCount>();
		ArrayList<String> a=new ArrayList<String>();
		List<ResultDTO> resdto2=new ArrayList<ResultDTO>();
		
		for(String id:ids)
		{
			if (userRepository.existsById(id)) 
			{ 
				userRepository.deleteById(id);
				a.add(id);
				successCount++;
				count.setSuccessCount(String.valueOf(successCount));
				count.setSuccess(a);
				List<SuccessCount> lst1=new ArrayList<SuccessCount>();
				lst1.add(count);
				
			}
			else {
				errorCount++;
				count1.setErrorCount(String.valueOf(errorCount));
				ResultDTO resdto1 = new ResultDTO();
				resdto1.setCode("user002");
				resdto1.setMessage("id not exist ");
				resdto1.setData(id);
				resdto2.add(resdto1);
				count1.setFailure(resdto2);
				count1.setFailure(resdto2);
			}
		}
		List<Object> obj=new ArrayList<Object>();
		obj.add(count);
		obj.add(count1);
		resdto.setCode("User003");
		resdto.setMessage("One or More Objects are Not Processed");
		resdto.setData(obj);
		return resdto;
		
		
		
	}
//list and Filter
	public  ResultDTO getAllUser(int page, int size) {
	Pageable page1 = PageRequest.of(page, size);
	Page<User> page2 = userRepository.findAll(page1);
	List<User> user = new ArrayList<User>();
	for(User d:page2)
	{
	user.add(d);
	}
	ResultDTO res = new ResultDTO();
	res.setCode("dept001");
	res.setMessage("succesfully fetched");
	res.setData(user);
	return res;
	}






}

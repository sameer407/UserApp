package com.zkteco.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.zkteco.user.dto.ResultDTO;
import com.zkteco.user.dto.UserDTO;
import com.zkteco.user.entity.User;
import com.zkteco.user.exception.UserNotFoundException;
import com.zkteco.user.repository.UserRepository;

@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;
	
	//@MockBean
	//private UserRepository userRepository;

	User user=new User();
	
	/*
	 * @BeforeEach void setUp() { User user = User.builder()
	 * .userId("402890be7c9760b7017c97615e02000f")
	 * .firstName("Sameer").lastName("Quraish")
	 * .address("jpnagar").email_Id("sameer@gmail.com")
	 * .userCode("user-10").gender("male").build();
	 * Mockito.when(userRepository.fetchUserById("402890be7c9760b7017c97615e02000f")
	 * ).thenReturn(user); }
	 */
	
	ResultDTO found = new ResultDTO();

	@Test
	public void whenUserNotFound() throws UserNotFoundException {
		String userId="402890be7c9760b7017c97615e02000hg";
		found = userService.fetchUserById(userId);
		assertEquals(found.getMessage(), "User Details nt found");
	}
	
	@Test
	public void whenUserFound() throws Exception
	{
	
		 String userId="402890be7c9760b7017c976161640001";
		found = userService.fetchUserById(userId);
		assertEquals(found.getMessage(), "User Details found");
		
	}
	

	
	
	  @Test public void whenIdFound() throws Exception 
	  { 
		  String  userId="402890be7c9760b7017c97615e020000"; found =
				  userService.deleteUserById(userId);
		  		   assertEquals(found.getMessage(),"One or More Objects are Not Processed");
	  
	 }
	 
	 
		
		  @Test 
		  public void whenIdNotFound() throws Exception 
		  { 
			  String userId="402890be7c9760b7017c97616362000y"; found =
					  userService.deleteUserById(userId); assertEquals(found.getMessage(), "User id nt found");
		  
		 }
		 
	  
	  @Test
	  public void whenSuccessfullySave() throws UserNotFoundException
	  {
		  UserDTO user=new UserDTO();
		  user.setFirstName("firstNam");
		  user.setLastName("bbbbb");
		  user.setAddress("vinobanagar");
		  user.setEmail_Id("sameer@gmail.com");
		  user.setCode("user-01");
		  user.setGender("male");
		  found = userService.saveUser(user);
		  assertEquals(found.getMessage(),"User resource created successfully");
	  
	  }
	  @Test
	  public void whenFnameEmailaddressIsEmpty() throws UserNotFoundException
	  {
		  UserDTO user=new UserDTO();
		  user.setFirstName("");
		  user.setLastName("bbbbb");
		  user.setAddress("vinobanagar");
		  user.setEmail_Id("");
		  user.setCode("user-01");
		  user.setGender("male"); 
		  found = userService.saveUser(user);
		  assertEquals(found.getMessage(),"first name and address must not be empty");
		    
	  }
	  @Test
	  public void whenFnameIsEmpty() throws UserNotFoundException
	  {
		  UserDTO user=new UserDTO();
		  user.setFirstName("");
		  user.setLastName("bbbbb");
		  user.setAddress("vinobanagar");
		  user.setEmail_Id("sameer@gmail.com");
		  user.setCode("user-01");
		  user.setGender("male"); 
		  found = userService.saveUser(user);
		  assertEquals(found.getMessage(),"first name must not be null");
		  
	  }
	  @Test
	  public void whenEmailIsEmpty() throws UserNotFoundException
	  {
		  UserDTO user=new UserDTO();
		  user.setFirstName("sameer");
		  user.setLastName("bbbbb");
		  user.setAddress("vinobanagar");
		  user.setEmail_Id("");
		  user.setCode("user-01");
		  user.setGender("male"); 
		  found = userService.saveUser(user);
		  assertEquals(found.getMessage(),"Email should not be null");  
	  }
	  @Test
	  public void whenNameLessThenThree() throws UserNotFoundException
	  {
		  UserDTO user=new UserDTO();
		  user.setFirstName("sa");
		  user.setLastName("bbbbb");
		  user.setAddress("vinobanagar");
		  user.setEmail_Id("sameer@gmail.com");
		  user.setCode("user-01");
		  user.setGender("male"); 
		  found = userService.saveUser(user);
		  assertEquals(found.getMessage(),"usert Name must be min 3 characters max 10 characters");  
	  }
	@Test
	public void getAllUSers() throws UserNotFoundException
	{
			
		  UserDTO user=new UserDTO();
		  user.setFirstName("sameer");
		  user.setLastName("bbbbb");
		  user.setAddress("vinobanagar");
		  user.setEmail_Id("sameer@gmail.com");
		  user.setCode("user-01");
		  user.setGender("male");
		  found = userService.saveUser(user);
		  int page=0;
		  int size=5;
			found = userService.getAllUser(page,size);
			assertEquals(found.getMessage(),"succesfully fetched");  
	  
	}
	
	@Test
	public void successfullyUpdated() throws UserNotFoundException
	{
		UserDTO userdto=new UserDTO();
		userdto.setFirstName("sameer");
		userdto.setLastName("bbbbb");
		userdto.setAddress("vinobanagar");
		userdto.setEmail_Id("sameer@gmail.com");
		userdto.setCode("user-01");
		userdto.setGender("male");
		  
		  String userId="402890be7c9760b7017c976161640001";
		  found = userService.updateUser(userId,userdto);
		  assertEquals(found.getMessage(),"successfully updated user details"); 
		 
	}
	@Test
	public void userDetailNotFound() throws UserNotFoundException
	{		UserDTO userdto=new UserDTO();
			userdto.setFirstName("sameer");
			userdto.setLastName("bbbbb");
			userdto.setAddress("vinobanagar");
			userdto.setEmail_Id("sameer@gmail.com");
			userdto.setCode("user-01");
			userdto.setGender("male");
		  String userId="402890be7c9760b7017c97616164000u";
		  found = userService.updateUser(userId,userdto);
		  assertEquals(found.getMessage(),"User Details nt found"); 
		
	}
}

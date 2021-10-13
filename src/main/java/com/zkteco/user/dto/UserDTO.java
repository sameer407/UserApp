package com.zkteco.user.dto;

import java.util.Date;

//import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	private String userId;


	private String firstName;
	private String lastName;
	private String address;
	private String email_Id;
	private String userCode;
	private String gender;
	private Date createdate;
	private Date updatedate;
	public void setCode(String string) {
		
		System.out.println("hello user");
	}
	public void setMessage(String string) {
		
		
	}
	public void setData(UserDTO userDTO) {
	
		
	}
}

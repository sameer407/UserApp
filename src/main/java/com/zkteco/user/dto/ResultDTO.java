package com.zkteco.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
	private String code;
	//@Value("${my.mess}")
	private String message;
	private Object data;
	//private String firstName;
	//private String lastName;
	//private String address;
	//private String email_Id;
	//private String userCode;
	//private String gender;

}

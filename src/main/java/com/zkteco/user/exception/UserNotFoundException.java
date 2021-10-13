package com.zkteco.user.exception;

public class UserNotFoundException extends Exception{
	public UserNotFoundException()
	{
		super();
	}
	public UserNotFoundException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}
	

public UserNotFoundException(String message, Throwable cause) {
super(message, cause);
}



public UserNotFoundException(Throwable cause) {
super(cause);
}



protected UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
boolean writableStackTrace) {
super(message, cause, enableSuppression, writableStackTrace);
}
	
	
}

package com.zkteco.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.zkteco.user.entity.ErrorMessage;

@ControllerAdvice
@ResponseStatus
public class RestRepositoryEntityExceptionHandler {

@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ErrorMessage> EmployeeNotFoundException(UserNotFoundException exception,
WebRequest request) 
{
ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

}
}

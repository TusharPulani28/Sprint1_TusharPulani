package com.demo.controller;

import java.util.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status , WebRequest request){
		
//		name:"IT SHOULD BE WITHIN 50 CHARATERS"
			
		Map<String,String> errors = new HashMap<>();
//		getBindingResult to bind all the exception
//		getAllErrors to get all the error in the list
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			//getField() - it return the affected field that has the error it return the fieldError object i.e we need to typecast it
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
	}
}

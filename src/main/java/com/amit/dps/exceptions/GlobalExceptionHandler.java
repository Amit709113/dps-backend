package com.amit.dps.exceptions;


import org.springframework.security.access.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amit.dps.payloads.ApiResponse;//

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
//		String fieldName=ex.getFieldName();
//		double fieldValue=ex.getFieldValue();
		ApiResponse apiResponse=new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	
	
	
//	entityNotFoundException is caught
//	@ExceptionHandler(EntityNotFoundException.class)
//	public ResponseEntity<ApiResponse> entityNotFoundExceptionHandler(){
//		ApiResponse apiResponse=new ApiResponse("user not found", false);
//		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotFoundException(MethodArgumentNotValidException ex){
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message= error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse>handleAccessDenied(AccessDeniedException error){
		String message=error.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<ApiResponse>handleUnexpectedTypeException(UnexpectedTypeException error){
		String message=error.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}


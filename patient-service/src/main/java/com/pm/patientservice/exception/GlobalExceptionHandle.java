package com.pm.patientservice.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandle.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(
				error->errors.put(error.getField(),error.getDefaultMessage())
			);
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Map<String,String>> handleEmailAlreadyException(EmailAlreadyExistsException ex){
		log.warn("Email address already Exist {}",ex.getMessage());
		return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email address already exists"));
		
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex){
		log.warn("Patient Not Found {}",ex.getMessage());
		return ResponseEntity.badRequest().body(Collections.singletonMap("error", ex.getMessage()));
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handleException(Exception ex){
		return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid Request Data"));
		
	}
	
	
}

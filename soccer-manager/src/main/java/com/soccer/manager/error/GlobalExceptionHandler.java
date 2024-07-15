package com.soccer.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(value = {AccessDeniedException.class})
	 public ResponseEntity<Void> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
		 return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	 }
	 
	 @ExceptionHandler(value = {UserAlreadyExistException.class})
	 public ResponseEntity<Void> handleAccessDeniedException(UserAlreadyExistException e, WebRequest request) {
		 return ResponseEntity.status(HttpStatus.CONFLICT).build();
	 }
}

package com.ajay.evdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<MessageDto> handleNotFoundException(NoDataFoundException e) {
		MessageDto error = MessageDto.builder()
							.code(HttpStatus.NOT_FOUND.value()+"")
							.message(e.getMessage())
							.build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(BadDataException.class)
	public ResponseEntity<MessageDto> handleBadDataException(BadDataException e) {
		MessageDto error = MessageDto.builder()
							.code(HttpStatus.BAD_REQUEST.value()+"")
							.message(e.getMessage())
							.build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
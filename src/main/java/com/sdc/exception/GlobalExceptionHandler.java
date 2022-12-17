package com.sdc.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> resourceNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {
		List<String> list = ex.getFieldErrors().stream().map(x -> x.getDefaultMessage()).toList();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), listToString(list), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private static String listToString(List<String> stringList) {
		StringBuilder builder = new StringBuilder();
		for (String s : stringList) {
			builder.append(s).append(" ");
		}
		return builder.toString().trim();
	}

}

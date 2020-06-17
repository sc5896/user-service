package com.nagp.as.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.nagp.as.exception.ASErrEnum;
import com.nagp.as.exception.ASException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(ASException.class)
	public ResponseEntity<String> TechnicalException(ASException ex, WebRequest request) {
		request.getDescription(false);
		ASErrEnum asErrEnum = ex.getAuthErrEnum();
		logger.error(asErrEnum.toString(), ex);
		return new ResponseEntity<>(ASErrEnum.AS02.getErrorMsg(), asErrEnum.getStatus());
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> Exception(Throwable ex, WebRequest request) {
		request.getDescription(false);
		logger.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ASErrEnum.AS01.getErrorMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
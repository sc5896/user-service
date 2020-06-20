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

/**
 * Global exception handler utility to gracefully handle any runtime exception
 * thrown by the system.
 * 
 * @author santoshkumar02
 *
 */
@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	/**
	 * This method handles custom exception thrown by the system and generates
	 * suitable api response by extracting error code, error message and http
	 * status from exception
	 * 
	 * @param ex
	 * @param request
	 * @return http response entity
	 */
	@ExceptionHandler(ASException.class)
	public ResponseEntity<String> TechnicalException(ASException ex, WebRequest request) {
		request.getDescription(false);
		ASErrEnum asErrEnum = ex.getAuthErrEnum();
		logger.error(asErrEnum.toString(), ex);
		return new ResponseEntity<>(ASErrEnum.AS02.getErrorMsg(), asErrEnum.getStatus());
	}

	/**
	 * This method handles unchecked runtime exception thrown by the system and
	 * generates suitable api response with http status code 500.
	 * 
	 * @param ex
	 * @param request
	 * @return http response entity
	 */
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> Exception(Throwable ex, WebRequest request) {
		request.getDescription(false);
		logger.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ASErrEnum.AS01.getErrorMsg(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
package com.nagp.as.exception;

import org.springframework.http.HttpStatus;

public enum ASErrEnum {

	AS01("AS01", "Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR),
	AS02("AS02", "User not found", HttpStatus.NO_CONTENT),
	AS03("AS03", "Error in calling user-service", HttpStatus.INTERNAL_SERVER_ERROR),
	AS04("AS04", "Error in calling order-service", HttpStatus.INTERNAL_SERVER_ERROR);
	
    private final String errorCode;
    private final String errorMsg;
    private HttpStatus status;
    
	private ASErrEnum(String errorCode, String errorMsg, HttpStatus status) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public String toString() {
		return this.errorCode+":"+this.errorMsg;
	}

}
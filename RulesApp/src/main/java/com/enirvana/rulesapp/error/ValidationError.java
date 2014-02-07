package com.enirvana.rulesapp.error;

public class ValidationError {
	public static final Integer invalid_status = 404; 
	
	public Integer status;
	public Integer errorCode;
	public String message;

	public ValidationError(Integer statuc, Integer errorCode, String message) {
		super();
		this.status = statuc;
		this.errorCode = errorCode;
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer statuc) {
		this.status = statuc;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

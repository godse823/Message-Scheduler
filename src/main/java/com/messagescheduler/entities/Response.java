package com.messagescheduler.entities;

import javax.persistence.Entity;


public class Response {
	private Integer code;
	private String Message;
	
	
	public Response() {

	}

	public Response(Integer code, String message) {
		this.code = code;
		Message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", Message=" + Message + "]";
	}
	
	
	

}

package com.messagescheduler.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.messagescheduler.annotations.Phone;
import com.messagescheduler.annotations.ValidDateTime;


public class Request {
	
	@NotNull
	@NotEmpty(message = "Please Enter The Message. (Message cannot be empty)")
	private String message;
	public Request(String message, String phoneNumber, String scheduledTime) {
		super();
		this.message = message;
		this.phoneNumber = phoneNumber;
		this.scheduledTime = scheduledTime;
	}
	@Phone
	private String phoneNumber;
	@ValidDateTime
	private String scheduledTime;
	
	public Request() {
		
	}

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	@Override
	public String toString() {
		return "Request [message=" + message + ", phoneNumber=" + phoneNumber + ", scheduledTime=" + scheduledTime
				+ "]";
	}	

}

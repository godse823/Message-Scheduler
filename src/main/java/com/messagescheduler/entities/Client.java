package com.messagescheduler.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Client {

	private int clientId;
	private String clientName;
	private String token;
	
	


	public Client(int clientId, String clientName, String token) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.token = token;
	}


	public Client() {
		
	}
	public int getClientId() {
		return clientId;
	}


	public void setClientId(int clientId) {
		this.clientId = clientId;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", token=" + token + "]";
	}
	
	
	
	
}

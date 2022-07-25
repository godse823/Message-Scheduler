package com.messagescheduler.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class Message {
	Integer message_id;
	String message;
	String scheduled_at;
	String destination_phone_number;
	Integer client_id;	
	String created_at;
	

	boolean scheduled_status;
	boolean pending_status;
	String submitted_at;

	boolean submitted_status;
	String whatsapp_api_message_id;
	
	public Message() {
		super();
	}

	public Message(Integer message_id, String message, String scheduled_at, String destination_phone_number, Integer client_id,
			String created_at, boolean scheduled_status, boolean pending_status, String submitted_at,
			boolean submitted_status, String whatsapp_api_message_id) {
		this.message_id = message_id;
		this.message = message;
		this.scheduled_at = scheduled_at;
		this.destination_phone_number = destination_phone_number;
		this.client_id = client_id;
		this.created_at = created_at;
		this.scheduled_status = scheduled_status;
		this.pending_status = pending_status;
		this.submitted_at = submitted_at;
		this.submitted_status = submitted_status;
		this.whatsapp_api_message_id = whatsapp_api_message_id;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getScheduled_at() {
		return scheduled_at;
	}

	public void setScheduled_at(String scheduled_at) {
		this.scheduled_at = scheduled_at;
	}

	public String getDestination_phone_number() {
		return destination_phone_number;
	}

	public void setDestination_phone_number(String destination_phone_number) {
		this.destination_phone_number = destination_phone_number;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public boolean isScheduled_status() {
		return scheduled_status;
	}

	public void setScheduled_status(boolean scheduled_status) {
		this.scheduled_status = scheduled_status;
	}

	public boolean isPending_status() {
        return pending_status;
    }

    public void setPending_status(boolean pending_status) {
        this.pending_status = pending_status;
    }

	public String getSubmitted_at() {
		return submitted_at;
	}

	public void setSubmitted_at(String submitted_at) {
		this.submitted_at = submitted_at;
	}

	public boolean isSubmitted_status() {
		return submitted_status;
	}

	public void setSubmitted_status(boolean submitted_status) {
		this.submitted_status = submitted_status;
	}

	public String getWhatsapp_api_message_id() {
		return whatsapp_api_message_id;
	}

	public void setWhatsapp_api_message_id(String whatsapp_api_message_id) {
		this.whatsapp_api_message_id = whatsapp_api_message_id;
	}

	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", message=" + message + ", scheduled_at=" + scheduled_at
				+ ", destination_phone_number=" + destination_phone_number + ", client_id=" + client_id
				+ ", created_at=" + created_at + ", scheduled_status=" + scheduled_status + ", pending_status="
				+ pending_status + ", submitted_at=" + submitted_at + ", submitted_status=" + submitted_status
				+ ", whatsapp_api_message_id=" + whatsapp_api_message_id + "]";
	}	
	
}

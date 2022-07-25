package com.messagescheduler.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.messagescheduler.dao.MessageDao;
import com.messagescheduler.entities.Client;
import com.messagescheduler.entities.Message;
import com.messagescheduler.entities.Request;
import com.messagescheduler.exception.SQLErrorException;

@Service
public class MessageService {
	
    Logger logger = LoggerFactory.getLogger(MessageService.class);

    
	@Autowired
	MessageDao messageDao;
	
	
	public MessageService(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public int saveMessage(Request requestbody, Client client) throws SQLErrorException {
		logger.info("MessageDao : save message");

		return messageDao.insertMessageInDB(requestbody, client);
	}
	
	public int updateMessageStatus(Boolean pending_status,Boolean submitted_status,String whatsAppMessageId,LocalDateTime submitted_at,Integer message_id) throws SQLErrorException {
		logger.info("MessageDao : update message status");
		return messageDao.updateMessageInDB(pending_status, submitted_status, whatsAppMessageId, submitted_at, message_id);
	}
	
	public List<Message> pollMessageFromDB()throws SQLErrorException {
		logger.info("MessageDao : poll message");
		return messageDao.findAllMessage();
	}
}

package com.messagescheduler.dao;

//import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.messagescheduler.exception.SQLErrorException;
import com.messagescheduler.entities.Client;
import com.messagescheduler.entities.Message;
import com.messagescheduler.entities.Request;
import com.messagescheduler.rowmappers.MessageMapper;

@Repository
public class MessageDao {
	
	Logger logger  = LoggerFactory.getLogger(ClientDao.class);

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//inserting into database
	
	public int insertMessageInDB(Request requestBody, Client client)  throws SQLErrorException {
		int result =0;
		String query = "insert into message(message,scheduled_at,destination_phone_number,client_id,created_at,pending_status,scheduled_status) values(?,?,?,?,?,?,?)";
		try {
			logger.info("Query--> " + query);
			result = jdbcTemplate.update(query,requestBody.getMessage(),requestBody.getScheduledTime(),requestBody.getPhoneNumber(),client.getClientId(),LocalDateTime.now(),true,true);
			return result;
		}
		catch(Exception e) {
            logger.warn("Exception: "+e.getMessage());
 
            throw new SQLErrorException("error while doing sql operation");
		}
		finally{
			System.out.println("finally block");
		}
	}
	
	//update the status
	
	public int updateMessageInDB(Boolean pending_status,Boolean submitted_status,String whatsAppMessageId,LocalDateTime submitted_at,Integer message_id)  throws SQLErrorException {
		String query = "UPDATE message set pending_status=?,submitted_status=?,submitted_at=?,whatsapp_api_message_id=? where message_id=?";
		int result = 0;
		try {
			logger.info("Query--> " + query);
			result = jdbcTemplate.update(query,pending_status,submitted_status,submitted_at,whatsAppMessageId,message_id);
			return result;
		}
		catch(Exception e) {
            logger.warn("Exception: "+e.getMessage());

            throw new SQLErrorException("error while doing sql operation");
		}
		finally{
			System.out.println("finally block");
		}
	}
	
	//create list of all message in upcomming one minutes
	
	public List<Message> findAllMessage() throws SQLErrorException {
		String query = "select * from message where pending_status =true and scheduled_at < current_timestamp";
        List<Message> messages = Collections.emptyList();
		try {
			logger.info("Query--> " + query);
			messages= jdbcTemplate.query(query, new MessageMapper());
			return messages;
		}
		catch(Exception e) {
            logger.warn("Exception: "+e.getMessage());

            throw new SQLErrorException("error while doing sql operation");
		}
		finally{
			System.out.println("finally block");
		}
	}
}

package com.messagescheduler.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messagescheduler.dao.ClientDao;
import com.messagescheduler.entities.Client;
import com.messagescheduler.exception.SQLErrorException;

@Service
public class ClientService {
    Logger logger = LoggerFactory.getLogger(ClientService.class);

	
	@Autowired
	ClientDao clientDao;
	
	public ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public Client validToken(String token) throws SQLErrorException {
		logger.info("ClientDao : Valid Token");

		Client client =  clientDao.findClientWithToken(token);
		return client;
	}
}

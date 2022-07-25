package com.messagescheduler.dao;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.messagescheduler.entities.Client;
import com.messagescheduler.exception.SQLErrorException;
import com.messagescheduler.services.MessageService;

@SpringBootTest
class ClientDaoTest {
	
	@Autowired
	private ClientDao clientDao;
	
	Logger logger = LoggerFactory.getLogger(MessageService.class);
	
    @Test
    void getClientUsingTokenInvalid() throws SQLErrorException {
        Client actualResult = null;
        try {
            actualResult = clientDao.findClientWithToken("Invalid");
            System.out.println("actualresult " + actualResult);
        } 
        catch (Exception e) {
        	logger.warn(e.getMessage());
            throw new SQLErrorException("sql error while validating client using token");
        }
    }
}

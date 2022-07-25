package com.messagescheduler.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.messagescheduler.entities.Client;
import com.messagescheduler.entities.Message;
import com.messagescheduler.entities.Request;
import com.messagescheduler.exception.SQLErrorException;

@SpringBootTest
class MessageDaoTest {

	@Autowired
	private MessageDao messageDao;

	@Test
	void insertionTesting()throws SQLErrorException  {
		Client client = new Client(1,"temp","dummy");
		Request request = new Request("Hii","9359370619","2022-03-30T18:05:20");
		int actual = messageDao.insertMessageInDB(request, client);
		assertThat(actual).isEqualTo(1);
	}

    @Test
    void insetMessageAsClientIsNull() {
        Client dummyclient = new Client(101,"dummy","dummytoken");
        Request request = new Request("dummy message for testing purpose", "7972757302", "2022-03-30T18:05:20");
        int actualResult = 0;
        try {
           actualResult = messageDao.insertMessageInDB(request,null);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

	
	@Test
	void updationTesting()throws SQLErrorException  {
		int actual = messageDao.updateMessageInDB(false,true,"dummy wp id",LocalDateTime.now(),1);
		assertThat(actual).isEqualTo(1);
	}
	
	
	@Test
	void getAllTesting() throws SQLErrorException {
		List<Message> actual = messageDao.findAllMessage();
		assertThat(actual).isNotNull();
	}
	

	
}

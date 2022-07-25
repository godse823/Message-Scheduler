package com.messagescheduler.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.messagescheduler.dao.MessageDao;
import com.messagescheduler.entities.Client;
import com.messagescheduler.entities.Message;
import com.messagescheduler.entities.Request;
import com.messagescheduler.exception.SQLErrorException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

	@MockBean
	private MessageDao messageDao;
	private MessageService messageService;
	
	@BeforeEach
	void setup() {
		this.messageService = new MessageService(this.messageDao);
	}
	
	@Test
	void saveMessageTesting() throws SQLErrorException {
		Client client = new Client(1,"test","dummy");
		Request request = new Request("Hii","9359370619","2022-04-30T15:45:20");
		when(messageDao.insertMessageInDB(request, client)).thenReturn(1);
		int actual = messageDao.insertMessageInDB(request, client);
		assertThat(actual).isEqualTo(1);
	}
	
	@Test
	void updateMessageStatusTesting()throws SQLErrorException  {
		when(messageDao.updateMessageInDB(any(), any(), any(), any(), any())).thenReturn(1);
		int actual = messageDao.updateMessageInDB(any(), any(), any(), any(), any());
		assertThat(actual).isEqualTo(1);
	}
	
	@Test
	void pollAllMessageTesting() throws SQLErrorException {
		List<Message>expected = Collections.emptyList();
		when(messageDao.findAllMessage()).thenReturn(expected);
		List<Message>actual = messageDao.findAllMessage();
		assertThat(actual).isEqualTo(expected);
	}

}

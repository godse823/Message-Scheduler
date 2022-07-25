package com.messagescheduler.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.messagescheduler.dao.ClientDao;
import com.messagescheduler.entities.Client;
import com.messagescheduler.exception.SQLErrorException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
	
	@MockBean
	private ClientDao clietDao;

	private ClientService clientService;
	
	@BeforeEach
	void setup() {
		this.clientService = new ClientService(this.clietDao);
	}
	
	Client actual = new Client(1,"temp","token");

	
	@Test
	void clientServiceTestingForSimillarToken() throws SQLErrorException {

		when(clietDao.findClientWithToken("token")).thenReturn(actual);

		Client expected = clientService.validToken("token");
		System.out.println(actual);
		System.out.println(expected);
		assertThat(expected).isEqualTo(actual);
	}
	
	@Test
	void clientServiceTestingForDissimillarToken() throws SQLErrorException {
		when(clietDao.findClientWithToken("wrongtoken")).thenReturn(null);
		Client expected = clientService.validToken("wrongtoken");
		assertThat(expected).isNull();;
	}
}

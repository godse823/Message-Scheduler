package com.messagescheduler.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagescheduler.entities.Client;
import com.messagescheduler.entities.Request;
import com.messagescheduler.entities.Response;
import com.messagescheduler.services.ClientService;
import com.messagescheduler.services.MessageService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class MessageControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	MessageService messageService;
	
	@MockBean
	ClientService clientService;
	
	public ObjectMapper object = new ObjectMapper();
		
	@Test
	void authenticationTesting() throws Exception {
		Request request = new Request("Hii","9359370619","2022-04-30T15:45:20");
		String jsonString = object.writeValueAsString(request);
		MvcResult res = mvc.perform(post("/schedule/message").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();
	//	MvcResult res = mvc.perform(post("/schedule/message").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();
		String actualresult	= res.getResponse().getContentAsString();
		Response response = object.readValue(actualresult, Response.class);
		assertThat(response.getCode()).isEqualTo(1001);
		assertThat(response.getMessage()=="Authentication Failed !!");
	}
	
	@Test
	void authenticationForInvalidTesting() throws Exception {
		Request request = new Request("Hii","9359370619","2022-04-30T15:45:20");
		String jsonString = object.writeValueAsString(request);
		when(clientService.validToken(any())).thenReturn(null);
		MvcResult res = mvc.perform(post("/schedule/message").header("token", "invalid").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();
		String actualresult	= res.getResponse().getContentAsString();
		Response response = object.readValue(actualresult, Response.class);
		assertThat(response.getCode()).isEqualTo(1001);
		assertThat(response.getMessage()=="Authentication Failed !!");

	}
	
	@Test
	void phoneNumberValidationTesting() throws Exception
	{
		Client client = new Client(1,"temp","dummytoken");
		when(clientService.validToken("dummytoken")).thenReturn(client);
		Request request = new Request("Hii","","2022-04-30T15:45:20");
		String jsonString = object.writeValueAsString(request);
		MvcResult res = mvc.perform(post("/schedule/message").contentType(MediaType.APPLICATION_JSON_VALUE).header("token", "dummytoken").content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String actualresult	= res.getResponse().getContentAsString();
		Response response = object.readValue(actualresult, Response.class);
		assertThat(response.getCode()).isEqualTo(1002);
		assertThat(response.getMessage()=="phone is invalid!!");
	}
	
	@Test
	void timeValidationTesting() throws Exception {

		Client client = new Client(1,"temp","dummytoken");
		when(clientService.validToken("dummytoken")).thenReturn(client);
		Request request = new Request("Hii","9359370619","2022-04-30TTemp");
		String jsonString = object.writeValueAsString(request);
		MvcResult res = mvc.perform(post("/schedule/message").contentType(MediaType.APPLICATION_JSON_VALUE).header("token", "dummytoken").content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String actualresult	= res.getResponse().getContentAsString();
		Response response = object.readValue(actualresult, Response.class);
		assertThat(response.getCode()).isEqualTo(1002);
		assertThat(response.getMessage()=="Scheduled Time is invalid!!");
	}
	
	@Test
	void messageSentTesting() throws Exception {
		Client client = new Client(1,"temp","dummytoken");
		when(clientService.validToken("dummytoken")).thenReturn(client);
		when(messageService.saveMessage(any(), any())).thenReturn(1);
		Request request = new Request("Hii","9359370619","2022-04-30T15:45:20");
		String jsonString = object.writeValueAsString(request);
		MvcResult res = mvc.perform(post("/schedule/message").contentType(MediaType.APPLICATION_JSON_VALUE).header("token", "dummytoken").content(jsonString).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String actualresult	= res.getResponse().getContentAsString();
		Response response = object.readValue(actualresult, Response.class);
		assertThat(response.getCode()).isEqualTo(1000);
		assertThat(response.getMessage()=="Message Scheduled !!!");

	}
}

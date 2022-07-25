package com.messagescheduler.interceptors;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagescheduler.entities.Client;
import com.messagescheduler.entities.Response;
import com.messagescheduler.services.ClientService;

@Component
public class ClientInceptors implements HandlerInterceptor{
	
    Logger logger = LoggerFactory.getLogger(ClientInceptors.class);

    
	@Autowired
	ClientService clientService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        logger.info("Inside interceptors ");
		String token = request.getHeader("token");
		Client client = clientService.validToken(token);
		if(client ==null) {
            logger.info("Authentication failed(Client is NULL).");
			response.setContentType("application/json");
			response.setStatus(400);
			Response resp = new Response(1001,"Authentication Failed !!");
			String responseString = new ObjectMapper().writeValueAsString(resp);
			PrintWriter out = response.getWriter();
			out.print(responseString);
			return false;
		}
        logger.info("Authentication Success and client is: " + client);
		request.setAttribute("client", client);
		return true;
	}
	
}
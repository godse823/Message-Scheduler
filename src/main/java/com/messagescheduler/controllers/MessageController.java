package com.messagescheduler.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messagescheduler.entities.Client;
import com.messagescheduler.entities.Request;
import com.messagescheduler.entities.Response;
import com.messagescheduler.services.MessageService;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping("/schedule/")
public class MessageController {
	
	Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * method : GET
	 * testing path : /schedule/test
	 * purpose : method to test rest api is working fine or not
	 * @return : response
	 */
	
	@GetMapping("/test")
	public Response testingRoute(){
		Response r= null;
		logger.info("Testing sample");
		try {
			
			r = new Response(200,"Successful Test!!!");
			return r;
		}
		catch(Exception e) {
			r = new Response(1004,"Something Went Wrong!!");
			e.printStackTrace();
		}
		finally{
			System.out.println("finally block");
		}
		return r;
	}
	
	/**
	 * method : post
	 * Input Parameter : Request, HttpServletRequest
	 * testing path : /schedule/message
	 * purpose : Scheduling the message recieved from request and return the reponse whether scheduled or not
	 * @return : response
	 * 
	 */
	
	@PostMapping("/message")
	public Response scheduleMessageHandler(@RequestBody @Valid Request requestbody,HttpServletRequest request){
		Response r = null;
		logger.info("Scheduling message request");
		try {			
			Client client = (Client) request.getAttribute("client");
			int res = messageService.saveMessage(requestbody,client);
            logger.info("Messages Scheduled " + res);

			r = new Response(1000,"Message Scheduled !!!");
			return r;
		}
		catch(Exception e) {
            logger.warn("Exception Occured " + e.getMessage());
			r = new Response(1004,"Something Went Wrong!!");
		//	e.printStackTrace();
		}
		finally{
			System.out.println("finally block");
		}
		return r;
	}
	
	
	/**
	 * input : Map<String,Object>
	 * testing path : /schedule/update
	 * purpose : for setting callback url
	 * @param obj
	 */
	
    @PostMapping("/update")
    public void updateStatus(@RequestBody Map<String, Object> obj){	
    	logger.info("Callback url");
        if(!obj.get("type").equals("message-event")) return;
        Map<String, Object> payload = (Map<String, Object>) obj.get("payload");
        System.out.println(payload);
    }
	
    /**
     * This is a method to handle the validation exception occured during the program execution
     * @param e
     * @return : response
     */
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	Response onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		logger.info("Validation Exception Occured");
        FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
        String errorMessage = fieldError.getDefaultMessage();
        Response response = new Response(1002, errorMessage);
        return response;
    }
}

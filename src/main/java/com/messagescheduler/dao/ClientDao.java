package com.messagescheduler.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.messagescheduler.entities.Client;
import com.messagescheduler.rowmappers.ClientMapper;

@Repository
public class ClientDao {
	
	Logger logger  = LoggerFactory.getLogger(ClientDao.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// checking the client with given token
	public Client findClientWithToken(String token) {
		String query = "select * from client where token=?";
		Client client = null;
		try {
			logger.info("Query--> " + query);
        //    logger.info("Query result--> " + client.toString());
			client = jdbcTemplate.queryForObject(query, new ClientMapper(),token);
			return client;
		}
		catch(Exception e) {
            logger.warn("Exception: "+e.getMessage());
			e.printStackTrace();
			//throw new SQLE
			throw e;
		}
		finally{
			System.out.println("finally block");
		}
	}
	
}

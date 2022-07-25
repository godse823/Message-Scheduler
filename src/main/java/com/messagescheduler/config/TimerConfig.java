package com.messagescheduler.config;

import java.util.Timer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimerConfig {
	 @Bean
	    public Timer getTimer() {
	        return new Timer();
	    }
}

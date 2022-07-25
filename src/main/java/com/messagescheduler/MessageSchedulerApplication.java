package com.messagescheduler;

import java.io.IOException;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import com.messagescheduler.timerFunction.Task;

@SpringBootApplication
@ComponentScan({"com.*"})
public class MessageSchedulerApplication {
	
	@Autowired
	public Task task;
	@Autowired
	public Timer timer;

	public static void main(String[] args) throws InterruptedException, IOException  {
		SpringApplication.run(MessageSchedulerApplication.class, args);
	}

    @EventListener(ApplicationReadyEvent.class)
    public void startScheduler() {
        timer.schedule(task, 1000, 60000);
    }

}

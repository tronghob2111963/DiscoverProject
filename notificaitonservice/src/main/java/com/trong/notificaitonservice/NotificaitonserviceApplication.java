package com.trong.notificaitonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.trong.notificaitonservice" , "com.trong.commonservice"})
public class NotificaitonserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificaitonserviceApplication.class, args);
	}

}

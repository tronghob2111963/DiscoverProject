package com.trong.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
<<<<<<< HEAD
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.trong.bookservice" , "com.trong.commonservice"})
=======

@SpringBootApplication
@EnableDiscoveryClient
>>>>>>> 3ac60f58ee77c519e2afa7ad3ecf50f14e218cd3
public class BookserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}

}

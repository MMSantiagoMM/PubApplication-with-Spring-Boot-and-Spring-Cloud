package com.training.mypubmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MyPubMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPubMongoApplication.class, args);
	}

}

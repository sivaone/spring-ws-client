package com.github.sivaone.springwsclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWsClientApplication implements CommandLineRunner {

	@Autowired
	WebServiceClient client = new WebServiceClient();

	public static void main(String[] args) {
		SpringApplication.run(SpringWsClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		client.sendAndReceive();
	}
}

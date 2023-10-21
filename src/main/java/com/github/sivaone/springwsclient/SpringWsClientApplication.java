package com.github.sivaone.springwsclient;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWsClientApplication implements CommandLineRunner {

	private final XLogger log = XLoggerFactory.getXLogger(SpringWsClientApplication.class);
	@Autowired
	private WebServiceClient client;

	public static void main(String[] args) {
		SpringApplication.run(SpringWsClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("Starting command");
		log.entry(args[0]);
		String capitalCity = null;
		String currency = null;
		try {
			capitalCity = client.getCapitalCity(args[0]);
			currency = client.getCountryCurrency(args[0]);
		} catch (RuntimeException ex) {
			log.catching(ex);
		}

		log.exit(capitalCity);
	}
}

package com.yatoooon.managementsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ManagementsystemApplication {
	protected final static Logger logger = LoggerFactory.getLogger(ManagementsystemApplication.class);
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ManagementsystemApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		logger.info("ManagementsystemApplication is success!");
		System.err.println("quick start. http://localhost:8080");
	}
}

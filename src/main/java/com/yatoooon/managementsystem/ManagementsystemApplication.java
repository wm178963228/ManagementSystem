package com.yatoooon.managementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yatoooon.managementsystem.dao*")
@SpringBootApplication
public class ManagementsystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagementsystemApplication.class, args);
	}
}

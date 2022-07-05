package com.inditex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories("com.inditex.repositories")
public class PruebaInditexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaInditexApplication.class, args);
	}
}

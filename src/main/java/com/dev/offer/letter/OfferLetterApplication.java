package com.dev.offer.letter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OfferLetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferLetterApplication.class, args);
	}
}

package com.az.bookstore;

import com.az.bookstore.constant.Constants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = Constants.API_TITLE, version = Constants.API_VERSION,
		description = Constants.API_DESCRIPTION))
@SpringBootApplication
public class AzBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzBookstoreApplication.class, args);
	}
}
package com.burakpekisik.ecommerce_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class EcommerceBackendApplication {

	public static void main(String[] args) {
		 SpringApplication app = new SpringApplication(EcommerceBackendApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		app.run(args);
	}

}

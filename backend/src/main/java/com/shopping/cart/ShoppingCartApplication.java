package com.shopping.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/calculate-grand-total").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/calculate-subtotal-and-tax-total").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/calculate-taxable-subtotal").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/apply-coupons").allowedOrigins("http://localhost:4200");


			}
		};
	}

}

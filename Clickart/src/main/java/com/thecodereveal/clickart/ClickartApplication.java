package com.thecodereveal.clickart;

//import lombok.Value;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories
public class ClickartApplication {

	@Value("${stripe.secret}")
	private String stripeSecret;

	public static void main(String[] args) {
		SpringApplication.run(ClickartApplication.class, args);

	}

	@PostConstruct
	public void init(){
		Stripe.apiKey = this.stripeSecret;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOriginPatterns(Collections.singletonList("*")); // Insecure, but for demo purposes it's ok
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "responseType", "Authorization", "x-authorization", "content-range","range"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		config.setExposedHeaders(Arrays.asList("X-Total-Count", "X-Total-Count", "content-range", "Content-Type", "Accept", "X-Requested-With", "remember-me"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}


}

package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class OauthSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthSampleApplication.class, args);
	}
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	@RequestMapping("/")
	public Object getStuff() {
		return restTemplate.getForObject("http://localhost:8081", Object.class);
	}
}

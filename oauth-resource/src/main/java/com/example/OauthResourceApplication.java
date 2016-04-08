package com.example;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@RestController
@EnableResourceServer
public class OauthResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthResourceApplication.class, args);
	}
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping("/")
	public Object hello() throws Exception {
		
		OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
		String jwtValue = details.getTokenValue();
		String payloadBase64 = jwtValue.split("\\.")[1];
		Map<String,Object> payload = objectMapper.readValue(Base64Utils.decodeFromString(payloadBase64), new TypeReference<Map<String,Object>>() {
		});
		Map<String,Object> response = new LinkedHashMap<>();
		response.put("message", "here's the JWT payload");
		response.put("payload", payload);
		return response;
	}
}

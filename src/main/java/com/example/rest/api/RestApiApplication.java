package com.example.rest.api;


import com.sun.net.httpserver.HttpHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.*;


@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
		String userurl = "http://91.241.64.178:7081/api/users";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(userurl, HttpMethod.GET, entity, String.class);
		String result = response.getBody();
		System.out.println(result);
		
		HttpHeaders headers1 = response.getHeaders();
		String set_cookie = headers1.getFirst(headers.SET_COOKIE);

		HttpHeaders headers2 = new HttpHeaders();
		headers2.set("Content-Type", "application/json");
		headers2.set("Cookie", set_cookie);


		User user = new User( (long) 3,"James","Brown", (byte) 33);
		HttpEntity<User> entity1 = new HttpEntity<User>(user,headers2);
		ResponseEntity<String> response1 =  restTemplate.exchange(userurl, HttpMethod.POST, entity1, String.class);
		String result1 = response1.getBody();
		System.out.println(result1);

		User user1 = new User( (long) 3,"Thomas","Shelby", (byte) 33);
		HttpEntity<User> entity2 = new HttpEntity<User>(user1,headers2);
		ResponseEntity<String> response2 =  restTemplate.exchange(userurl, HttpMethod.PUT, entity2, String.class);
		String result2 = response2.getBody();
		System.out.println(result2);

		ResponseEntity<String> response3 =  restTemplate.exchange("http://91.241.64.178:7081/api/users/3", HttpMethod.DELETE, entity2, String.class);
		String result3 = response3.getBody();
		System.out.println(result3);



    	}
	}






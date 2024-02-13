package com.apitest.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JWTService {

    private final RestTemplate restTemplate = new RestTemplate();

  
    public String getToken() {
        String username = "user";
        String password = "password";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(username, password);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9001/token", requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return "Une erreur s'est produite lors de la demande. Code d'état : " + response.getStatusCodeValue();
        }
    }
    
 
}

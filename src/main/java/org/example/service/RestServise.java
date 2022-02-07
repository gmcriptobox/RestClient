package org.example.service;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@Service
public class RestServise {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://91.241.64.178:7081/api/users";
    HttpHeaders headers = new HttpHeaders();



    private void getAllUser(){
        ResponseEntity<String> response =
                restTemplate.exchange(URL, HttpMethod.GET, null
                        , String.class);

        headers.add("Cookie", response.getHeaders().getFirst(HttpHeaders.SET_COOKIE));
    }

    private String saveUser(User user){
        HttpEntity <User> entity = new HttpEntity<User>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity
                , String.class);
        return response.getBody();
    }

    private String putUser(User user){
        HttpEntity <User> entity = new HttpEntity<User>(user, headers);
        ResponseEntity<String> response = null;
        try {

             response = restTemplate.exchange(URL, HttpMethod.PUT, entity
                    , String.class);
        } catch (HttpClientErrorException e){
            return e.getResponseBodyAsString();
        }
        return response.getBody();
    }

    private String deleteUser(User user){
        HttpEntity <User> entity = new HttpEntity<User>(user, headers);
        ResponseEntity<String> response = null;
        try{
            response = restTemplate.exchange(URL +"/" +user.getId(), HttpMethod.DELETE, entity
                , String.class);
        } catch (HttpClientErrorException e){
            return e.getResponseBodyAsString();
        }
        return response.getBody();
    }

    public String getResult(){
        StringBuilder str = new StringBuilder();
        User user = new User((long)3,"James", "Brown", (byte)30);

        getAllUser();
        str.append(saveUser(user));
        user.setName("Thomas");
        user.setLastName("Shelby");
        str.append(putUser(user));
        str.append(deleteUser(user));
        return str.toString();
    }
}

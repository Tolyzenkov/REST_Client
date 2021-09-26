package org.example;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    List<String> cookies;
    private final HttpHeaders headers = new HttpHeaders();
    private final String URL = "http://91.241.64.178:7081/api/users";

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> showAllUsers() {
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        String.class);
       cookies = responseEntity.getHeaders().get("Set-Cookie");
        return responseEntity;
    }

    public void addUser(User user) {
        headers.set("Cookie",cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity =
        restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(responseEntity);
        System.out.println("Юзер добавлен");
    }
    public void editUser(User user) {
        headers.set("Cookie",cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity =
        restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(responseEntity);
        System.out.println("Юзер изменен");
    }

    public void deleteUser(long id) {
        headers.set("Cookie",cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =
        restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println(responseEntity);
        System.out.println("Юзер удален");
    }


}

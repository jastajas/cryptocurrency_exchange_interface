package com.example.cryptocurrency_exchange_interface.request_service;

import com.example.cryptocurrency_exchange_interface.model.SymbolsDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class RestRequests {

    private RestTemplate restTemplate;

    public RestRequests() {
        this.restTemplate = new RestTemplate();
    }

    public List<Object> getObjectsList(String url, HttpMethod method) {

        return restTemplate.exchange(
                url,
                method,
                null,
                new ParameterizedTypeReference<List<Object>>() {
                }).getBody();
    }

    public Object getSimpleObject(String url, HttpMethod method) {

        return restTemplate.exchange(
                url,
                method,
                null,
                new ParameterizedTypeReference<Object>() {
                }).getBody();
    }


}

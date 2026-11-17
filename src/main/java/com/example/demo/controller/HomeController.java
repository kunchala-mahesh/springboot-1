package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("message", "Spring Boot REST API is running successfully.");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("Users", "/users");
        endpoints.put("Posts", "/posts");
        endpoints.put("Products", "/products");

        response.put("endpoints", endpoints);
        return response;
    }
}

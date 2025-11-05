package com.example.smart_employee_management.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/departments/external")
public class ExternalApiController {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public ExternalApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public JsonNode getExternalDepartments() throws Exception {
        // example external API — returns JSON
        String url = "https://dummyjson.com/users";
        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = mapper.readTree(response);
        // return the "users" array (or adapt to return what you need)
        return root.get("users");
    }
}

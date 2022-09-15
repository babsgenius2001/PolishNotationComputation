package com.sinchapi.api.controller;

import com.sinchapi.api.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    private ApiService apiService;

    @PostMapping("/evaluate")
    public ResponseEntity<?> createNewApplication(@RequestBody String expression) {
        try {
            if (expression.isEmpty() || expression.equals("\n")) {

                Map<String, String> errorMessage = new HashMap<>();
                errorMessage.put("error", "Kindly enter an expression!");

                return new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(apiService.evaluateExpression(expression), HttpStatus.OK);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

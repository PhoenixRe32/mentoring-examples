package com.pittacode.restspringtemp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {

    @GetMapping(produces = "text/plain")
    public ResponseEntity<String> getClichedMessage() {
        String message = "Hello, World!";
        return ResponseEntity.ok(message);
    }
}
package com.example.chatapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hi")
public class TestController {

    @GetMapping()
    public ResponseEntity<String> sayHi() {
        return ResponseEntity.ok("Hi");
    }
}

package com.scaler.spring.javabasics.helloworld;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public class HelloWorldController {

    @GetMapping("/hello/{userId}")
    public ResponseEntity<String> sayHello(@PathVariable String userId) {
        return ResponseEntity.ok("Hello " + userId + ", hope you are doing well!");
    }
}

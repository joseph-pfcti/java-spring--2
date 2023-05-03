package com.pfcti.springwebservices.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/login")
public class LoginController {

    @GetMapping
    public ResponseEntity<Void> login () {
        return ResponseEntity.ok().build();
    }
}

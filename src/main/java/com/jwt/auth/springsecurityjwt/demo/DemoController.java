package com.jwt.auth.springsecurityjwt.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
    // This endpoint requires Authentication (Bearer)
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("true");
    }
}

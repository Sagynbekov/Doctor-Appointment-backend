package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        boolean authenticated = userService.authenticate(request.getUsername(), request.getPassword());
        if (authenticated) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String result = userService.register(request.getUsername(), request.getPassword());
        if ("ok".equals(result)) {
            return ResponseEntity.ok().body("Registration successful");
        } else if ("exists".equals(result)) {
            return ResponseEntity.status(409).body("Username already exists");
        } else {
            return ResponseEntity.status(500).body("Registration error");
        }
    }
}

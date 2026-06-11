package com.devops.pipelinemonitor.controller;

import com.devops.pipelinemonitor.dto.LoginRequest;
import com.devops.pipelinemonitor.dto.RegisterRequest;
import com.devops.pipelinemonitor.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String result = authService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String result = authService.login(request);
        return ResponseEntity.ok(result);
    }
}

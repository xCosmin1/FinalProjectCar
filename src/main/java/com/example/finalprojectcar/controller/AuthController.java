package com.example.finalprojectcar.controller;

import com.example.finalprojectcar.dto.request.RegisterRequest;
import com.example.finalprojectcar.service.UniqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/controller")
public class AuthController {
    private final UniqueService uniqueService;



    public AuthController(UniqueService uniqueService, AuthenticationManager authenticationManager) {
        this.uniqueService = uniqueService;

    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            uniqueService.registerCustomer(registerRequest);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
}

package com.devops.pipelinemonitor.service;

import com.devops.pipelinemonitor.JwtUtil;
import com.devops.pipelinemonitor.dto.LoginRequest;
import com.devops.pipelinemonitor.dto.RegisterRequest;
import com.devops.pipelinemonitor.entity.User;
import com.devops.pipelinemonitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already taken!";
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered!";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : "USER");

        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(LoginRequest request) {

        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            return "User not found!";
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Invalid password!";
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
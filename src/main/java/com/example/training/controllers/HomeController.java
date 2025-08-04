package com.example.training.controllers;

import com.example.training.JWT.JWTUtil;
import com.example.training.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepo;
    private final JWTUtil jwtUtils;

    @GetMapping
    public ResponseEntity<?> getHomeInfo(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtils.extractUsername(token);

        return userRepo.findByUsername(username)
                .map(user -> ResponseEntity.ok(Map.of(
                        "username", user.getUsername(),
                        "role", user.getRole().getName()
                )))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}

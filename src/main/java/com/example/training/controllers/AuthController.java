package com.example.training.controllers;

import com.example.training.DTO.RegisterRequest;
import com.example.training.JWT.JWTUtil;
import com.example.training.DTO.LoginRequest;
import com.example.training.DTO.LoginResponse;
import com.example.training.models.User;
import com.example.training.repositories.UserRepository;
import com.example.training.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final JWTUtil jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtUtils.generateToken(user.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtils.extractUsername(token);

        Optional<User> user = userRepo.findByUsername(username);
        return user.map(u -> ResponseEntity.ok(Map.of(
                "username", u.getUsername(),
                "avatarurl", u.getAvatarURL(),
                "email", u.getEmail(),
                "phoneNumber", u.getPhoneNumber()
        ))).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}

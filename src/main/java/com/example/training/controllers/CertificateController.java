package com.example.training.controllers;

import com.example.training.models.Certificate;
import com.example.training.services.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping("/issue")
    public ResponseEntity<Certificate> issue(
            @RequestParam Long userId,
            @RequestParam Long courseId,
            @RequestParam String url) {
        return ResponseEntity.ok(certificateService.issue(userId, courseId, url));
    }

    @GetMapping("/user/{userId}")
    public List<Certificate> getByUser(@PathVariable Long userId) {
        return certificateService.getByUser(userId);
    }
}

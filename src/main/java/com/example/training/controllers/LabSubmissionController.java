package com.example.training.controllers;

import com.example.training.models.LabSubmission;
import com.example.training.services.LabSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class LabSubmissionController {

    private final LabSubmissionService labSubmissionService;

    @PostMapping("/submit")
    public ResponseEntity<LabSubmission> submit(
            @RequestParam Long userId,
            @RequestParam Long labId,
            @RequestBody String code) {
        return ResponseEntity.ok(labSubmissionService.submit(userId, labId, code));
    }

    @GetMapping("/user/{userId}")
    public List<LabSubmission> getByUser(@PathVariable Long userId) {
        return labSubmissionService.getByUser(userId);
    }

    @GetMapping("/lab/{labId}")
    public List<LabSubmission> getByLab(@PathVariable Long labId) {
        return labSubmissionService.getByLab(labId);
    }

    @GetMapping("/user/{userId}/lab/{labId}")
    public ResponseEntity<LabSubmission> getByUserAndLab(@PathVariable Long userId, @PathVariable Long labId) {
        return labSubmissionService.getByUserAndLab(userId, labId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}


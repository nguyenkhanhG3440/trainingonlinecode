package com.example.training.controllers;

import com.example.training.models.Progress;
import com.example.training.services.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<Progress> create(@RequestBody Progress progress) {
        return ResponseEntity.ok(progressService.create(progress));
    }

    @GetMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<Progress> getByUserAndCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        return progressService.getByUserAndCourse(userId, courseId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Progress> update(@PathVariable Long id, @RequestBody Progress progress) {
        return ResponseEntity.ok(progressService.update(id, progress));
    }
}

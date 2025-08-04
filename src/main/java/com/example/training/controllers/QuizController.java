package com.example.training.controllers;

import com.example.training.models.Quiz;
import com.example.training.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<Quiz> create(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.create(quiz));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable Long id) {
        return quizService.getById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Quiz> getAll() {
        return quizService.getAll();
    }

    @GetMapping("/course/{courseId}")
    public List<Quiz> getByCourse(@PathVariable Long courseId) {
        return quizService.getByCourse(courseId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> update(@PathVariable Long id, @RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.update(id, quiz));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quizService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.training.controllers;

import com.example.training.models.QuizAnswer;
import com.example.training.services.QuizAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-answers")
@RequiredArgsConstructor
public class QuizAnswerController {

    private final QuizAnswerService quizAnswerService;

    @PostMapping("/submit")
    public ResponseEntity<QuizAnswer> submit(
            @RequestParam Long userId,
            @RequestParam Long quizId,
            @RequestParam String selectedAnswer) {
        return ResponseEntity.ok(quizAnswerService.submit(userId, quizId, selectedAnswer));
    }

    @GetMapping
    public List<QuizAnswer> getAll() {
        return quizAnswerService.getAll();
    }
}

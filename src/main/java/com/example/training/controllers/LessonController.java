package com.example.training.controllers;

import com.example.training.models.Lesson;
import com.example.training.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<Lesson> create(@RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.create(lesson));
    }

    @GetMapping
    public List<Lesson> getAll() {
        return lessonService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getById(@PathVariable Long id) {
        return lessonService.getById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public List<Lesson> getByCourse(@PathVariable Long courseId) {
        return lessonService.getByCourse(courseId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> update(@PathVariable Long id, @RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.update(id, lesson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

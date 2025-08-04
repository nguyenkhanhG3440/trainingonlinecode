package com.example.training.controllers;

import com.example.training.models.Lab;
import com.example.training.services.LabService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labs")
@RequiredArgsConstructor
public class LabController {

    private final LabService labService;

    @PostMapping
    public ResponseEntity<Lab> create(@RequestBody Lab lab) {
        return ResponseEntity.ok(labService.create(lab));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lab> getById(@PathVariable Long id) {
        return labService.getById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Lab> getAll() {
        return labService.getAll();
    }

    @GetMapping("/course/{courseId}")
    public List<Lab> getByCourse(@PathVariable Long courseId) {
        return labService.getByCourse(courseId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lab> update(@PathVariable Long id, @RequestBody Lab lab) {
        return ResponseEntity.ok(labService.update(id, lab));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        labService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

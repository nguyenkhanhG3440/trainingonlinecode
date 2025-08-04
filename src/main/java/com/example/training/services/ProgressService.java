package com.example.training.services;

import com.example.training.models.Progress;
import com.example.training.repositories.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepo;

    public Progress create(Progress progress) {
        return progressRepo.save(progress);
    }

    public Optional<Progress> getByUserAndCourse(Long userId, Long courseId) {
        return progressRepo.findByUserIdAndCourseId(userId, courseId);
    }

    public Progress update(Long id, Progress progress) {
        Progress existing = progressRepo.findById(id).orElseThrow();
        existing.setCompleted(progress.isCompleted());
        existing.setScore(progress.getScore());
        return progressRepo.save(existing);
    }
}

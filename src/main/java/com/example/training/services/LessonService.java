package com.example.training.services;

import com.example.training.models.Lesson;
import com.example.training.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepo;

    public Lesson create(Lesson lesson) {
        return lessonRepo.save(lesson);
    }

    public List<Lesson> getAll() {
        return lessonRepo.findAll();
    }

    public Optional<Lesson> getById(Long id) {
        return lessonRepo.findById(id);
    }

    public List<Lesson> getByCourse(Long courseId) {
        return lessonRepo.findByCourseId(courseId);
    }

    public Lesson update(Long id, Lesson lesson) {
        Lesson existing = lessonRepo.findById(id).orElseThrow();
        existing.setTitle(lesson.getTitle());
        existing.setContent(lesson.getContent());
        existing.setCourse(lesson.getCourse());
        return lessonRepo.save(existing);
    }

    public void delete(Long id) {
        lessonRepo.deleteById(id);
    }
}


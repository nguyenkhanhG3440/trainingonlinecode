package com.example.training.services;

import com.example.training.models.Course;
import com.example.training.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepo;

    public Course create(Course course) {
        return courseRepo.save(course);
    }

    public Optional<Course> getById(Long id) {
        return courseRepo.findById(id);
    }

    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    public Course update(Long id, Course course) {
        Course existing = courseRepo.findById(id).orElseThrow();
        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        return courseRepo.save(existing);
    }

    public void delete(Long id) {
        courseRepo.deleteById(id);
    }
}


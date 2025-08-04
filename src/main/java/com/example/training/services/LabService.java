package com.example.training.services;

import com.example.training.models.Lab;
import com.example.training.repositories.LabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LabService {

    private final LabRepository labRepo;

    public Lab create(Lab lab) {
        return labRepo.save(lab);
    }

    public Optional<Lab> getById(Long id) {
        return labRepo.findById(id);
    }

    public List<Lab> getByCourse(Long courseId) {
        return labRepo.findByCourseId(courseId);
    }

    public List<Lab> getAll() {
        return labRepo.findAll();
    }

    public Lab update(Long id, Lab lab) {
        Lab existing = labRepo.findById(id).orElseThrow();
        existing.setTitle(lab.getTitle());
        existing.setDescription(lab.getDescription());
        existing.setTestCases(lab.getTestCases());
        existing.setExpectedOutput(lab.getExpectedOutput());
        existing.setCourse(lab.getCourse());
        return labRepo.save(existing);
    }

    public void delete(Long id) {
        labRepo.deleteById(id);
    }
}

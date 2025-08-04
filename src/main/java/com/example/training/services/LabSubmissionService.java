package com.example.training.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.training.models.Lab;
import com.example.training.models.LabSubmission;
import com.example.training.models.User;
import com.example.training.repositories.LabRepository;
import com.example.training.repositories.LabSubmissionRepository;
import com.example.training.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabSubmissionService {

    private final LabSubmissionRepository subRepo;
    private final LabRepository labRepo;
    private final UserRepository userRepo;

    public LabSubmission submit(Long userId, Long labId, String code) {
        Lab lab = labRepo.findById(labId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();

        LabSubmission sub = new LabSubmission();
        sub.setLab(lab);
        sub.setUser(user);
        sub.setCode(code);
        sub.setScore(0.0);
        sub.setResult("Pending");

        return subRepo.save(sub);
    }

    public List<LabSubmission> getByUser(Long userId) {
        return subRepo.findByUserId(userId);
    }

    public List<LabSubmission> getByLab(Long labId) {
        return subRepo.findByLabId(labId);
    }

    public Optional<LabSubmission> getByUserAndLab(Long userId, Long labId) {
        return subRepo.findByUserIdAndLabId(userId, labId);
    }
}


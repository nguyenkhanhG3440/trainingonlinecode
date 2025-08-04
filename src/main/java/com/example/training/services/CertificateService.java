package com.example.training.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.training.models.Certificate;
import com.example.training.models.Course;
import com.example.training.models.User;
import com.example.training.repositories.CertificateRepository;
import com.example.training.repositories.CourseRepository;
import com.example.training.repositories.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepo;
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;

    public Certificate issue(Long userId, Long courseId, String url) {
        User user = userRepo.findById(userId).orElseThrow();
        Course course = courseRepo.findById(courseId).orElseThrow();

        Certificate cert = new Certificate();
        cert.setUser(user);
        cert.setCourse(course);
        cert.setUrl(url);
        return certificateRepo.save(cert);
    }

    public List<Certificate> getByUser(Long userId) {
        return certificateRepo.findByUserId(userId);
    }
}



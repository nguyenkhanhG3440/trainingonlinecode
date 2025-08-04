package com.example.training.repositories;

import com.example.training.models.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUserIdAndCourseId(Long userId, Long courseId);
    List<Progress> findByUserId(Long userId);
}


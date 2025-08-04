package com.example.training.repositories;

import com.example.training.models.LabSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabSubmissionRepository extends JpaRepository<LabSubmission, Long> {
    List<LabSubmission> findByUserId(Long userId);
    List<LabSubmission> findByLabId(Long labId);
    Optional<LabSubmission> findByUserIdAndLabId(Long userId, Long labId);
}

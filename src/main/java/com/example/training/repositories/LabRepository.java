package com.example.training.repositories;

import com.example.training.models.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
    List<Lab> findByCourseId(Long courseId);
}

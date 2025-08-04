package com.example.training.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lab_submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabSubmission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String code;

    private String result;
    private Double score;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

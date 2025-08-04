package com.example.training.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String avatarURL;
    private String email;
    private String phoneNumber;


    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;


    @OneToMany(mappedBy = "user")
    private List<LabSubmission> submissions;

    @OneToMany(mappedBy = "user")
    private List<Progress> progresses;

    @OneToMany(mappedBy = "user")
    private List<QuizAnswer> quizAnswers;

    @OneToMany(mappedBy = "user")
    private List<Certificate> certificates;
}

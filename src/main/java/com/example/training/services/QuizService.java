package com.example.training.services;

import com.example.training.models.Quiz;
import com.example.training.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepo;

    public Quiz create(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    public List<Quiz> getByCourse(Long courseId) {
        return quizRepo.findByCourseId(courseId);
    }

    public Optional<Quiz> getById(Long id) {
        return quizRepo.findById(id);
    }

    public List<Quiz> getAll() {
        return quizRepo.findAll();
    }

    public Quiz update(Long id, Quiz quiz) {
        Quiz existing = quizRepo.findById(id).orElseThrow();
        existing.setQuestion(quiz.getQuestion());
        existing.setCorrectAnswer(quiz.getCorrectAnswer());
        existing.setCourse(quiz.getCourse());
        return quizRepo.save(existing);
    }

    public void delete(Long id) {
        quizRepo.deleteById(id);
    }
}


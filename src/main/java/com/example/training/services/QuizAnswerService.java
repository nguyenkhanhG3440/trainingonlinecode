package com.example.training.services;

import com.example.training.models.Quiz;
import com.example.training.models.QuizAnswer;
import com.example.training.models.User;
import com.example.training.repositories.QuizAnswerRepository;
import com.example.training.repositories.QuizRepository;
import com.example.training.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizAnswerService {

    private final QuizAnswerRepository answerRepo;
    private final UserRepository userRepo;
    private final QuizRepository quizRepo;

    public QuizAnswer submit(Long userId, Long quizId, String selectedAnswer) {
        User user = userRepo.findById(userId).orElseThrow();
        Quiz quiz = quizRepo.findById(quizId).orElseThrow();

        boolean correct = quiz.getCorrectAnswer().equalsIgnoreCase(selectedAnswer);

        QuizAnswer answer = new QuizAnswer();
        answer.setUser(user);
        answer.setQuiz(quiz);
        answer.setSelectedAnswer(selectedAnswer);
        answer.setCorrect(correct);

        return answerRepo.save(answer);
    }

    public List<QuizAnswer> getAll() {
        return answerRepo.findAll();
    }
}


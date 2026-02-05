package by.kotik.questions.service;

import by.kotik.questions.model.QuestionModel;

import java.util.Set;

public interface QuestionService {
    Set<QuestionModel> getApprovedQuestions();
}

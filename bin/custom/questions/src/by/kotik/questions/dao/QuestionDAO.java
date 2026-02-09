package by.kotik.questions.dao;

import by.kotik.questions.model.QuestionModel;

import java.util.List;

public interface QuestionDAO {
    List<QuestionModel> findDisapprovedAndNotSentByEmailQuestions();
}

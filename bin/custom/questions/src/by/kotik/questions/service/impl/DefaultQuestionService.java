package by.kotik.questions.service.impl;

import by.kotik.questions.dao.QuestionDAO;
import by.kotik.questions.model.QuestionModel;
import by.kotik.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DefaultQuestionService implements QuestionService {
    private final QuestionDAO questionDAO;

    @Autowired
    public DefaultQuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public Set<QuestionModel> getApprovedQuestions() {
        return new HashSet<>(questionDAO.findDisapprovedAndNotSentByEmailQuestions());
    }
}

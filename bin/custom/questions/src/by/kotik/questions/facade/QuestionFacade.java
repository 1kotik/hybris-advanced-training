package by.kotik.questions.facade;

import by.kotik.questions.model.QuestionModel;
import questions.data.QuestionEmailData;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface QuestionFacade {
    Map<String, List<QuestionEmailData>> groupQuestionsByProductId(Collection<QuestionModel> questions);
}

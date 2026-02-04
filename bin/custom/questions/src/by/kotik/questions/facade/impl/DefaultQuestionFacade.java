package by.kotik.questions.facade.impl;

import by.kotik.questions.facade.QuestionFacade;
import by.kotik.questions.model.QuestionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import questions.data.QuestionEmailData;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DefaultQuestionFacade implements QuestionFacade {
    private final Converter<QuestionModel, QuestionEmailData> questionEmailDataConverter;

    @Autowired
    public DefaultQuestionFacade(Converter<QuestionModel, QuestionEmailData> questionEmailDataConverter) {
        this.questionEmailDataConverter = questionEmailDataConverter;
    }

    @Override
    public Map<String, List<QuestionEmailData>> groupQuestionsByProductId(Collection<QuestionModel> questions) {
        return questions.stream()
                .filter(question -> "Staged".equals(question.getCatalogVersion().getVersion()))
                .map(questionEmailDataConverter::convert)
                .collect(Collectors.groupingBy(QuestionEmailData::getProductId));
    }
}

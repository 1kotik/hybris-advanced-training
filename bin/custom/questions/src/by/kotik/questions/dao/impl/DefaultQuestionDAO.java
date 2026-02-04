package by.kotik.questions.dao.impl;

import by.kotik.questions.dao.QuestionDAO;
import by.kotik.questions.model.QuestionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultQuestionDAO implements QuestionDAO {
    private final FlexibleSearchService flexibleSearchService;

    @Autowired
    public DefaultQuestionDAO(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<QuestionModel> findDisapprovedAndNotSentByEmailQuestions() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(
                String.format("SELECT {%s} FROM {%s} WHERE {%s}=false AND {%s}=true",
                        QuestionModel.PK, QuestionModel._TYPECODE, QuestionModel.SENTBYEMAIL, QuestionModel.APPROVED));
        return flexibleSearchService.<QuestionModel>search(query).getResult();
    }
}

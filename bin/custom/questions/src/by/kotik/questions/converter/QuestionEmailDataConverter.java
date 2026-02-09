package by.kotik.questions.converter;

import by.kotik.questions.model.QuestionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import questions.data.QuestionEmailData;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class QuestionEmailDataConverter implements Converter<QuestionModel, QuestionEmailData> {
    private final Populator<QuestionModel, QuestionEmailData> questionEmailDataPopulator;

    @Autowired
    public QuestionEmailDataConverter(Populator<QuestionModel, QuestionEmailData> questionEmailDataPopulator) {
        this.questionEmailDataPopulator = questionEmailDataPopulator;
    }

    @Override
    public QuestionEmailData convert(QuestionModel questionModel) throws ConversionException {
        QuestionEmailData questionEmailData = new QuestionEmailData();
        return convert(questionModel, questionEmailData);
    }

    @Override
    public QuestionEmailData convert(QuestionModel questionModel, QuestionEmailData questionEmailData) throws ConversionException {
        questionEmailDataPopulator.populate(questionModel, questionEmailData);
        return questionEmailData;
    }

    @Override
    public List<QuestionEmailData> convertAll(Collection<? extends QuestionModel> questionModels) throws ConversionException {
        return questionModels == null ? Collections.emptyList()
                : questionModels.stream().map(this::convert).toList();
    }
}

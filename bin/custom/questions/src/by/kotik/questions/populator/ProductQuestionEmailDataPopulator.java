package by.kotik.questions.populator;

import by.kotik.questions.model.QuestionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;
import questions.data.QuestionEmailData;

@Component
public class ProductQuestionEmailDataPopulator implements Populator<QuestionModel, QuestionEmailData> {
    @Override
    public void populate(QuestionModel questionModel, QuestionEmailData questionEmailData) throws ConversionException {
        if (questionModel == null || questionEmailData == null) {
            return;
        }
        questionEmailData.setQuestionId(questionModel.getCode());
        questionEmailData.setQuestion(questionModel.getQuestion());
        questionEmailData.setAnswer(questionModel.getAnswer());
        final ProductModel product = questionModel.getProduct();
        if (product != null) {
            questionEmailData.setProductId(product.getCode());
            questionEmailData.setProductName(product.getName());
        }
    }
}

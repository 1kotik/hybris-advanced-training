package by.kotik.questions.populator;

import by.kotik.questions.model.QuestionModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.stereotype.Component;
import questions.data.QuestionData;

import java.util.List;
import java.util.Set;

@Component("productQuestionDataPopulator")
public class ProductQuestionDataPopulator implements Populator<ProductModel, ProductData> {
    private final Converter<CustomerModel, CustomerData> customerConverter;

    public ProductQuestionDataPopulator(Converter<CustomerModel, CustomerData> customerConverter) {
        this.customerConverter = customerConverter;
    }

    @Override
    public void populate(final ProductModel productModel, final ProductData productData) throws ConversionException {
        if (productModel == null || productData == null) {
            return;
        }
        final Set<QuestionModel> questionModelList = productModel.getQuestions();
        final List<QuestionData> questionDataList = questionModelList.stream()
                .map(this::getQuestionData)
                .toList();
        productData.setQuestions(questionDataList);
    }

    private QuestionData getQuestionData(QuestionModel questionModel) {
        QuestionData questionData = new QuestionData();
        questionData.setId(questionModel.getCode());
        questionData.setQuestion(questionModel.getQuestion());
        questionData.setAnswer(questionModel.getAnswer());
        final CustomerData questionCustomer = customerConverter.convert(questionModel.getQuestionCustomer());
        final CustomerData answerCustomer = customerConverter.convert(questionModel.getAnswerCustomer());
        questionData.setQuestionCustomer(questionCustomer);
        questionData.setAnswerCustomer(answerCustomer);
        return questionData;
    }
}

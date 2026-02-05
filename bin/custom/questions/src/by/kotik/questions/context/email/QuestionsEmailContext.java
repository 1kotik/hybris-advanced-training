package by.kotik.questions.context.email;

import by.kotik.questions.facade.QuestionFacade;
import by.kotik.questions.model.QuestionsEmailingProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.springframework.beans.factory.annotation.Value;
import questions.data.QuestionEmailData;

import java.util.List;
import java.util.Map;

public class QuestionsEmailContext extends AbstractEmailContext<QuestionsEmailingProcessModel> {
    private Map<String, List<QuestionEmailData>> questionsEmailDataMap;
    private QuestionFacade questionFacade;
    @Value("${mail.from}")
    private String mailFromAddress;

    @Override
    protected BaseSiteModel getSite(QuestionsEmailingProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(QuestionsEmailingProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(QuestionsEmailingProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    @Override
    public void init(QuestionsEmailingProcessModel businessProcessModel, EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);
        questionsEmailDataMap = questionFacade.groupQuestionsByProductId(businessProcessModel.getQuestions());
        put(FROM_EMAIL, mailFromAddress);
        put(FROM_DISPLAY_NAME, "Support");
        put(DISPLAY_NAME, businessProcessModel.getCustomer().getName());
    }

    public Map<String, List<QuestionEmailData>> getQuestionsEmailDataMap() {
        return questionsEmailDataMap;
    }

    public void setQuestionFacade(QuestionFacade questionFacade) {
        this.questionFacade = questionFacade;
    }
}

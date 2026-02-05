package by.kotik.questions.action;

import by.kotik.questions.model.QuestionModel;
import by.kotik.questions.model.QuestionsEmailingProcessModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.task.RetryLaterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CheckEmailMessageStatusAndMarkQuestionsAsSentAction extends AbstractSimpleDecisionAction<QuestionsEmailingProcessModel> {
    private final ModelService modelService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckEmailMessageStatusAndMarkQuestionsAsSentAction.class);

    @Autowired
    public CheckEmailMessageStatusAndMarkQuestionsAsSentAction(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public Transition executeAction(QuestionsEmailingProcessModel questionsEmailingProcessModel) throws RetryLaterException, Exception {
        try {
            List<EmailMessageModel> emailMessages = questionsEmailingProcessModel.getEmails();
            boolean sentByEmail = emailMessages != null && emailMessages.stream().allMatch(EmailMessageModel::isSent);
            if (sentByEmail) {
                markQuestionsAsSent(questionsEmailingProcessModel);
                LOGGER.info("Marked questions as sent by email");
                return Transition.OK;
            }
            LOGGER.info("Email messages were not sent");
            return Transition.NOK;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Transition.NOK;
        }
    }

    private void markQuestionsAsSent(QuestionsEmailingProcessModel questionsEmailingProcessModel) {
        Set<QuestionModel> questions = questionsEmailingProcessModel.getQuestions();
        if (questions != null && !questions.isEmpty()) {
            questions.forEach(question -> question.setSentByEmail(true));
            modelService.saveAll(questions);
        }
    }
}

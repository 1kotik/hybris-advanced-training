package by.kotik.questions.job;

import by.kotik.questions.model.QuestionModel;
import by.kotik.questions.model.QuestionsEmailingProcessModel;
import by.kotik.questions.service.QuestionService;
import de.hybris.platform.commerceservices.customer.CustomerService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StartQuestionEmailingProcessJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartQuestionEmailingProcessJob.class);
    private final QuestionService questionService;
    private final BusinessProcessService businessProcessService;
    private final ModelService modelService;
    private final BaseStoreService baseStoreService;
    private final BaseSiteService baseSiteService;
    private final CommonI18NService commonI18NService;
    private final CustomerService customerService;
    @Value("${mail.recipient}")
    private String recipientAddress;
    @Value("${base.site}")
    private String baseSite;
    @Value("${base.store}")
    private String baseStore;

    public StartQuestionEmailingProcessJob(QuestionService questionService,
                                           BusinessProcessService businessProcessService,
                                           ModelService modelService,
                                           BaseStoreService baseStoreService,
                                           BaseSiteService baseSiteService,
                                           CommonI18NService commonI18NService,
                                           CustomerService customerService) {
        this.questionService = questionService;
        this.businessProcessService = businessProcessService;
        this.modelService = modelService;
        this.baseStoreService = baseStoreService;
        this.baseSiteService = baseSiteService;
        this.commonI18NService = commonI18NService;
        this.customerService = customerService;
    }

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        Set<QuestionModel> questions = questionService.getApprovedQuestions();
        if (questions.isEmpty()) {
            LOGGER.info("No questions found for sending");
            return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
        }
        String processName = "questionEmailingProcess";
        try {
            QuestionsEmailingProcessModel process = businessProcessService
                    .createProcess(processName + System.currentTimeMillis(), processName);
            process.setQuestions(questions);
            process.setLanguage(commonI18NService.getCurrentLanguage());
            process.setCurrency(commonI18NService.getCurrentCurrency());
            process.setSite(baseSiteService.getBaseSiteForUID(baseSite));
            process.setStore(baseStoreService.getBaseStoreForUid(baseStore));
            process.setCustomer(customerService.getCustomerByCustomerId(recipientAddress));
            modelService.save(process);
            businessProcessService.startProcess(process);
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        } catch (Exception e) {
            LOGGER.error("Exception occurred in process {}: {}", processName, e.getMessage(), e);
            return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
        }
    }
}

package by.kotik.questions.controllers.cms;

import by.kotik.questions.model.components.QuestionsCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.data.ProductData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@Controller("QuestionsCMSComponentController")
@RequestMapping("/view/QuestionsCMSComponentController")
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionsCMSComponentModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionsCMSComponentController.class);

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionsCMSComponentModel component) {
        LOGGER.info("Filling question content slot");
        ProductData productData = (ProductData) request.getAttribute("product");
        Integer numberOfQuestionsToShow = component.getNumberOfQuestionsToShow();
        if (productData != null) {
            model.addAttribute("questions",
                    productData.getQuestions().stream().limit(numberOfQuestionsToShow).toList());
        }
        model.addAttribute("numberOfQuestionsToShow", numberOfQuestionsToShow);
        model.addAttribute("fontSize", component.getFont());
    }

    @Override
    protected String getView(final QuestionsCMSComponentModel component) {
        return "addon:/questions/cms/questionscmscomponent";
    }
}

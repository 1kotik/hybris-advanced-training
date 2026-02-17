package by.kotik.questions.controllers.cms;

import by.kotik.questions.model.components.ProductWarrantyCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("ProductWarrantyCMSComponentController")
@RequestMapping("/view/ProductWarrantyCMSComponentController")
public class ProductWarrantyCMSComponentController extends AbstractCMSAddOnComponentController<ProductWarrantyCMSComponentModel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductWarrantyCMSComponentModel.class);

    @Override
    protected void fillModel(HttpServletRequest request, Model model, ProductWarrantyCMSComponentModel component) {
        LOGGER.info("Filling product warranty content slot");
    }

    @Override
    protected String getView(final ProductWarrantyCMSComponentModel component) {
        return "addon:/questions/cms/productwarrantycmscomponent";
    }
}

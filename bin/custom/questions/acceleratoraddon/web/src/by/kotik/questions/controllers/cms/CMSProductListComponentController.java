package by.kotik.questions.controllers.cms;

import de.hybris.platform.acceleratorcms.model.components.CMSProductListComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("CMSProductListComponentController")
@RequestMapping("/view/CMSProductListComponentController")
public class CMSProductListComponentController extends AbstractCMSAddOnComponentController<CMSProductListComponentModel> {
    @Override
    protected void fillModel(HttpServletRequest request, Model model, CMSProductListComponentModel component) {

    }

    @Override
    protected String getView(CMSProductListComponentModel component) {
        return "addon:/questions/cms/cmsproductlistcomponent";
    }
}

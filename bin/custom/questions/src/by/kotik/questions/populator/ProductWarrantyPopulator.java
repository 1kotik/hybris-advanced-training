package by.kotik.questions.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;
import de.hybris.platform.commercefacades.product.data.ProductData;

@Component("productWarrantyPopulator")
public class ProductWarrantyPopulator implements Populator<ProductModel, ProductData> {
    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        if (productModel == null || productData == null) {
            return;
        }
        Integer warrantyYears = productModel.getWarrantyYears();
        if (warrantyYears == null) {
            warrantyYears = 0;
        }
        productData.setWarrantyYears(warrantyYears);
    }
}

package by.kotik.questions.search.solrfacetsearch.populator;


import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

public class ProductWarrantyYearsValuePopulator extends SearchResultVariantProductPopulator {
    @Override
    public void populate(SearchResultValueData source, ProductData target) {
        super.populate(source, target);
        Object warrantyYears = source.getValues().get("warrantyYearsValue");
        if (warrantyYears instanceof Integer warrantyYearsIntegerValue) {
            target.setWarrantyYears(warrantyYearsIntegerValue);
        } else {
            target.setWarrantyYears(0);
        }
    }
}

package by.kotik.questions.search.solrfacetsearch.provider;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

public class ProductWarrantyYearsValueResolver extends AbstractValueResolver<ProductModel, Object, Object> {
    @Override
    protected void addFieldValues(InputDocument inputDocument, IndexerBatchContext indexerBatchContext, IndexedProperty indexedProperty, ProductModel productModel, ValueResolverContext<Object, Object> valueResolverContext) throws FieldValueProviderException {
        Integer warrantyYears = productModel.getWarrantyYears();
        if (warrantyYears == null) {
            warrantyYears = 0;
        }
        inputDocument.addField(indexedProperty, warrantyYears, valueResolverContext.getFieldQualifier());
    }
}

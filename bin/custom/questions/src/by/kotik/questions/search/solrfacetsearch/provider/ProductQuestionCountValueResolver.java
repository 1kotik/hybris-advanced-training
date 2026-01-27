package by.kotik.questions.search.solrfacetsearch.provider;

import by.kotik.questions.model.QuestionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

import java.util.Set;

public class ProductQuestionCountValueResolver extends AbstractValueResolver<ProductModel, Object, Object> {
    @Override
    protected void addFieldValues(
            InputDocument inputDocument, IndexerBatchContext indexerBatchContext,
            IndexedProperty indexedProperty, ProductModel productModel,
            ValueResolverContext<Object, Object> valueResolverContext)
            throws FieldValueProviderException {
        int questionCount = 0;
        Set<QuestionModel> questions = productModel.getQuestions();
        if (questions != null) {
            questionCount = questions.size();
        }
        inputDocument.addField(indexedProperty, questionCount, valueResolverContext.getFieldQualifier());
    }
}

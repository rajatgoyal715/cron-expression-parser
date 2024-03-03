package com.rajatgoyal.cronexpressionparser.transformers;

import com.rajatgoyal.cronexpressionparser.errors.InvalidCronExpressionException;
import com.rajatgoyal.cronexpressionparser.model.FieldType;

public interface Transformer {

    /**
     * Transforms the given expression to a readable description string
     * @param expression which needs to be transformed
     * @param fieldType which represents the type of the field
     * @return a readable string description
     * @throws InvalidCronExpressionException
     */
    String transform(String expression, FieldType fieldType) throws InvalidCronExpressionException;
}

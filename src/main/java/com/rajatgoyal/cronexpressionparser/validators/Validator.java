package com.rajatgoyal.cronexpressionparser.validators;

import com.rajatgoyal.cronexpressionparser.errors.InvalidCronExpressionException;

public interface Validator {

    public void validate(int[] values) throws InvalidCronExpressionException;
}

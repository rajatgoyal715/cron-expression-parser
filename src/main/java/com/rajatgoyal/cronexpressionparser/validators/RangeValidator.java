package com.rajatgoyal.cronexpressionparser.validators;

import com.rajatgoyal.cronexpressionparser.errors.InvalidCronExpressionException;

public class RangeValidator implements Validator {

    private final int start;
    private final int end;

    public RangeValidator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void validate(int[] values) throws InvalidCronExpressionException {
        for (int value : values) {
            if (value < start || value > end) throw new InvalidCronExpressionException("Values are out of range");
        }
    }
}

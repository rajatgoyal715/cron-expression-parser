package com.rajatgoyal.cronexpressionparser.transformers;

import com.rajatgoyal.cronexpressionparser.errors.InvalidCronExpressionException;
import com.rajatgoyal.cronexpressionparser.model.FieldType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FieldTransformer implements Transformer {

    List<Integer> values;
    FieldType fieldType;

    public FieldTransformer() {
        this.values = new ArrayList<>();
    }

    public String transform(String expression, FieldType fieldType) throws InvalidCronExpressionException {
        this.fieldType = fieldType;
        this.values = new ArrayList<>();

        if (expression.contains("-")) { // "1-5"
            transformRangeValues(expression);
        } else if (expression.startsWith("*")) { // "*/15"
            transformIntervalValues(expression);
        } else { // "0", "1,2,3"
            transformFixedValues(expression);
        }

        return generateStringFromValues();
    }

    private String generateStringFromValues() {
        return values.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private void transformRangeValues(String expression) throws InvalidCronExpressionException {
        String[] range = expression.split("-");
        if (range.length > 2) {
            throw new InvalidCronExpressionException("Range has more than 2 arguments");
        }
        int start = parseNumber(range[0]);
        int end = parseNumber(range[1]);
        for (int value = start; value <= end; value++) {
            if (isValueOutOfRange(value, this.fieldType.rangeStart, this.fieldType.rangeEnd))
                throw new InvalidCronExpressionException("Values are out of the range");
            this.values.add(value);
        }
    }

    private int parseNumber(String number) {
        return Integer.parseInt(number);
    }

    private void transformFixedValues(String expression) throws InvalidCronExpressionException {
        String[] values = expression.split(",");
        for (String value : values) {
            int intValue = parseNumber(value);
            if (isValueOutOfRange(intValue, this.fieldType.rangeStart, this.fieldType.rangeEnd))
                throw new InvalidCronExpressionException("Values are out of the range");
            this.values.add(intValue);
        }
    }

    private void transformIntervalValues(String expression) throws InvalidCronExpressionException {
        if (!expression.startsWith("*")) throw new InvalidCronExpressionException("Expression does not start with *");

        String[] intervals = expression.split("/");
        int intervalValue = 1;

        if (intervals.length > 2) throw new InvalidCronExpressionException("More than 1 / present in the expression");
        else if (intervals.length == 2) {
            intervalValue = parseNumber(intervals[1]);
        }
        for (int value = this.fieldType.rangeStart; value <= this.fieldType.rangeEnd; value += intervalValue) {
            this.values.add(value);
        }
    }

    private boolean isValueOutOfRange(int value, int rangeStart, int rangeEnd) {
        return value < rangeStart || value > rangeEnd;
    }

}

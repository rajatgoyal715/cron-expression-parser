package com.rajatgoyal.cronexpressionparser.transformers;

import com.rajatgoyal.cronexpressionparser.errors.InvalidCronExpressionException;
import com.rajatgoyal.cronexpressionparser.model.FieldType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTransformerTest {

    @Test
    public void FieldTransformerRangeValues() throws InvalidCronExpressionException {
        FieldTransformer fieldTransformer = new FieldTransformer();
        String transformedField;

        transformedField = fieldTransformer.transform("1-3", FieldType.MONTH);
        assertEquals("1 2 3", transformedField);
        transformedField = fieldTransformer.transform("1-3", FieldType.HOURS);
        assertEquals("1 2 3", transformedField);
        transformedField = fieldTransformer.transform("2-5", FieldType.DAY_OF_MONTH);
        assertEquals("2 3 4 5", transformedField);
        transformedField = fieldTransformer.transform("1-3", FieldType.DAY_OF_WEEK);
        assertEquals("1 2 3", transformedField);
        transformedField = fieldTransformer.transform("49-52", FieldType.MINUTES);
        assertEquals("49 50 51 52", transformedField);

        try {
            fieldTransformer.transform("49-52-57", FieldType.MINUTES);
        } catch (InvalidCronExpressionException exception) {
            assertEquals("Range has more than 2 arguments", exception.getMessage());
        }

        try {
            fieldTransformer.transform("49-61", FieldType.MINUTES);
        } catch (InvalidCronExpressionException exception) {
            assertEquals("Values are out of the range", exception.getMessage());
        }
    }

    @Test
    public void FieldTransformerFixedValues() throws InvalidCronExpressionException {
        FieldTransformer fieldTransformer = new FieldTransformer();
        String transformedField;

        // single value
        transformedField = fieldTransformer.transform("1", FieldType.MONTH);
        assertEquals("1", transformedField);
        // multiple values comma separated
        transformedField = fieldTransformer.transform("1,2,3", FieldType.MONTH);
        assertEquals("1 2 3", transformedField);

        try {
            fieldTransformer.transform("11,12,13", FieldType.MONTH);
        } catch (InvalidCronExpressionException exception) {
            assertEquals("Values are out of the range", exception.getMessage());
        }
    }

    @Test
    public void FieldTransformerIntervalValues() throws InvalidCronExpressionException {
        FieldTransformer fieldTransformer = new FieldTransformer();
        String transformedField;

        transformedField = fieldTransformer.transform("*", FieldType.MONTH);
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", transformedField);

        transformedField = fieldTransformer.transform("*/15", FieldType.MINUTES);
        assertEquals("0 15 30 45", transformedField);

        transformedField = fieldTransformer.transform("*/6", FieldType.MONTH); // every 6 months
        assertEquals("1 7", transformedField);

        try {
            fieldTransformer.transform("*/1/1", FieldType.MONTH);
        } catch (InvalidCronExpressionException exception) {
            assertEquals("More than 1 / present in the expression", exception.getMessage());
        }
    }
}
package com.rajatgoyal.cronexpressionparser.parser;

import com.rajatgoyal.cronexpressionparser.errors.InvalidCronExpressionException;
import com.rajatgoyal.cronexpressionparser.model.CronExpression;
import com.rajatgoyal.cronexpressionparser.model.FieldType;
import com.rajatgoyal.cronexpressionparser.transformers.FieldTransformer;

public class CronExpressionParserImpl implements CronExpressionParser {

    FieldTransformer fieldTransformer;

    public CronExpressionParserImpl() {
        this.fieldTransformer = new FieldTransformer();
    }

    @Override
    public CronExpression parse(String expression) throws InvalidCronExpressionException {
        String[] splitExpression = expression.trim().split(" ");
        if (splitExpression.length < 6) {
            throw new InvalidCronExpressionException("All arguments are not present");
        }

        String minuteExpression = splitExpression[0];
        String hoursExpression = splitExpression[1];
        String dayOfMonthExpression = splitExpression[2];
        String monthExpression = splitExpression[3];
        String dayOfWeekExpression = splitExpression[4];
        String commandExpression = splitExpression[5];

        String minutes = fieldTransformer.transform(minuteExpression, FieldType.MINUTES);
        String hours = fieldTransformer.transform(hoursExpression, FieldType.HOURS);
        String dayOfMonth = fieldTransformer.transform(dayOfMonthExpression, FieldType.DAY_OF_MONTH);
        String month = fieldTransformer.transform(monthExpression, FieldType.MONTH);
        String dayOfWeek = fieldTransformer.transform(dayOfWeekExpression, FieldType.DAY_OF_WEEK);

        return new CronExpression(minutes, hours, dayOfMonth, month, dayOfWeek, commandExpression);
    }
}

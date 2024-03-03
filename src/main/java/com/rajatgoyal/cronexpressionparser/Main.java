package com.rajatgoyal.cronexpressionparser;

import com.rajatgoyal.cronexpressionparser.errors.InvalidCronExpressionException;
import com.rajatgoyal.cronexpressionparser.model.CronExpression;
import com.rajatgoyal.cronexpressionparser.parser.CronExpressionParser;
import com.rajatgoyal.cronexpressionparser.parser.CronExpressionParserImpl;

public class Main {
    public static void main(String[] args) {
        try {

            String expression = args[0];
            CronExpressionParser cronExpressionParser = new CronExpressionParserImpl();

            CronExpression cronExpression = cronExpressionParser.parse(expression);
            System.out.println(cronExpression);

        } catch (InvalidCronExpressionException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
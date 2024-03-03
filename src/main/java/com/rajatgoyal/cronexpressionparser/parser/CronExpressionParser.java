package com.rajatgoyal.cronexpressionparser.parser;

import com.rajatgoyal.cronexpressionparser.errors.InvalidCronExpressionException;
import com.rajatgoyal.cronexpressionparser.model.CronExpression;

public interface CronExpressionParser {
    CronExpression parse(String expression) throws InvalidCronExpressionException;
}

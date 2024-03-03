package com.rajatgoyal.cronexpressionparser.model;

import static java.lang.String.format;

public class CronExpression {
    private final String minutes;

    private final String hours;
    private final String dayOfMonth;
    private final String month;
    private final String dayOfWeek;
    private final String command;

    public CronExpression(String minutes, String hours, String dayOfMonth, String month, String dayOfWeek, String command) {
        this.minutes = minutes;
        this.hours = hours;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.command = command;
    }

    @Override
    public String toString() {
        return format("%-14s%s\n", "minute", minutes) + format("%-14s%s\n", "hour", hours) + format("%-14s%s\n", "day of month", dayOfMonth) + format("%-14s%s\n", "month", month) + format("%-14s%s\n", "day of week", dayOfWeek) + format("%-14s%s\n", "command", command);
    }
}

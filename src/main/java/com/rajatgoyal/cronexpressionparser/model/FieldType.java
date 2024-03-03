package com.rajatgoyal.cronexpressionparser.model;

public enum FieldType {
    MINUTES(0, 59),
    HOURS(0, 23),
    DAY_OF_MONTH(1, 31),
    MONTH(1,12),
    DAY_OF_WEEK(1, 7);

    public final int rangeStart;
    public final int rangeEnd;

    FieldType(int rangeStart, int rangeEnd) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }
}

package com.yourssu.domain;

public class Coordinate {
    static final String COLUMN_LABELS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final int row;
    private final String column;

    public Coordinate(int row, String column) {
        this.row = row;
        this.column = column;
    }

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = COLUMN_LABELS.substring(column, column + 1);
    }

    int getRow() {
        return row;
    }

    String getColumn() {
        return this.column;
    }

    int getInternalColumn() {
        return COLUMN_LABELS.indexOf(this.column.toUpperCase());
    }
}

package com.yourssu.domain;

public enum Piece {
    BLACK,
    WHITE,
    EMPTY;

    public static Piece from(String value) {
        try {
            Piece piece = Piece.valueOf(value.toUpperCase());
            if (piece == EMPTY) {
                throw new IllegalArgumentException("EMPTY is not a valid player");
            }
            return piece;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid piece type: " + value + ". Must be BLACK or WHITE");
        }
    }
}

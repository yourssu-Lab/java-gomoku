package com.yourssu.console.view.implement;

import com.yourssu.domain.Piece;

public enum Symbol {
    BLACK("X", Piece.BLACK),
    WHITE("O", Piece.WHITE),
    EMPTY("#", Piece.EMPTY),
    ;

    private final String symbol;
    private final Piece piece;

    Symbol(String symbol, Piece piece) {
        this.symbol = symbol;
        this.piece = piece;
    }

    public static Symbol of(Piece piece) {
        for (Symbol symbol : Symbol.values()) {
            if (symbol.piece == piece) {
                return symbol;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getString() {
        return symbol;
    }
}

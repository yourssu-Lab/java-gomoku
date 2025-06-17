package com.yourssu.model;

public class GomokPlayer implements Player{

    private final Piece piece;

    public GomokPlayer(Piece piece) {
        this.piece = piece;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

}

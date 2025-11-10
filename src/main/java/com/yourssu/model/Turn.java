package com.yourssu.model;

public class Turn {
    private int currentTurn = 1;
    private Piece currentPiece = Piece.BLACK;

    public int getCurrentTurn() {
        return this.currentTurn;
    }

    public Piece getCurrnetPiece() {
        return this.currentPiece;
    }

    public void next() {
        currentTurn++;
        if (currentPiece.equals(Piece.BLACK)) {
            currentPiece = Piece.WHITE;
            return;
        }

        if (currentPiece.equals(Piece.WHITE)) {
            currentPiece = Piece.BLACK;
        }
    }
}

package com.yourssu.model;

public class GomokPlayer implements Player{

    private final Piece piece;
    private final Board board;

    public GomokPlayer(Piece piece, Board board) {
        this.piece = piece;
        this.board = board;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    @Override
    public void placePiece(int row, int column) {
        board.placePiece(row, column, piece);
    }
}

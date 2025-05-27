package com.yourssu.model;

public class GomokuBoard implements Board {

    private final int size;
    private final Piece[][] board;
    //private final Board board;

    public GomokuBoard(int size) {
        this.size = size;
        this.board = new Piece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public Piece getPiece(int row, int column) {
        return board[row][column];
    }

    @Override
    public void placePiece(int row, int column, Piece piece) {
        if (isAlreadyPlaced(row, column)) {
            throw new IllegalArgumentException();
        }
        board[row][column] = piece;
    }

    private Piece isWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Todo: Implement win checking logic
            }
        }
    }

    private boolean isAlreadyPlaced(int row, int column) {
        return board[row][column] != Piece.EMPTY;
    }

}

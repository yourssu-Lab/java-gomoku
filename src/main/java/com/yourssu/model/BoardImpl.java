package com.yourssu.model;

public class BoardImpl implements Board {
    private final Piece[][] board;


    public BoardImpl(Piece[][] boardState) {
        this.board = boardState;
    }

    public BoardImpl(int size) {
        if (size < 5 || size > 15) {
            throw new IllegalArgumentException();
        }

        Piece[][] boardState = new Piece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardState[i][j] = Piece.EMPTY;
            }
        }

        this.board = boardState;
    }

    @Override
    public Piece getPiece(int row, int column) {
        if (row > board.length || column > board.length) {
            throw new IllegalArgumentException();
        }
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException();
        }
        return this.board[row][column] ;
    }

    @Override
    public void placePiece(int row, int column, Piece piece) {
        if (row > board.length || column > board.length) {
            throw new IllegalArgumentException();
        }
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException();
        }
        this.board[row][column] = piece;
    }

    @Override
    public int getSize() {
        return this.board.length;
    }
}

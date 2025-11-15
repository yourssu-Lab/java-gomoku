package com.yourssu.domain;

import com.yourssu.console.view.implement.Symbol;

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

        if (this.board[row][column] != Piece.EMPTY) {
            throw new IllegalArgumentException();
        }

        this.board[row][column] = piece;
    }

    @Override
    public String[] toStringArray() {
        String[] result = getRows(board);

        return result;
    }

    @Override
    public int getSize() {
        return this.board.length;
    }

    private String[] getRows(Board board) {
        for (int row = 0; row < this.size; row++) {
            getRow(board, row);
        }
    }

    private String getRow(Board board, int row) {
        System.out.printf("%2d ", board.getSize() - row);
        for (int column = 0; column < board.getSize(); column++) {
            System.out.print(" " + Symbol.of(board.getPiece(row, column)).getString());
        }
        System.out.println();
    }
}

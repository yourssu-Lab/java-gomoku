package com.yourssu.model;

public class GomokuBoard implements Board {

    private final int size;
    private final Piece[][] board;

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

    @Override
    public boolean isGameEnd() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getPiece(i, j) != Piece.EMPTY && isSolved(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSolved(int row, int column) {
        for (int dir = 0; dir < 8; dir++) {
            if (checkedDirection(row, column, dir)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkedDirection(int row, int column, int dir) {
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        Piece currentPiece = getPiece(row, column);

        for (int i = 0; i < 5; i++) {
            int newRow = row + dx[dir] * i;
            int newColumn = column + dy[dir] * i;
            if (newRow < 0 || newRow >= size || newColumn < 0 || newColumn >= size) {
                return false;
            }
            if (currentPiece != getPiece(newRow, newColumn)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlreadyPlaced(int row, int column) {
        return board[row][column] != Piece.EMPTY;
    }
}

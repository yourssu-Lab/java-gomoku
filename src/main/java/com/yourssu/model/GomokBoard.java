package com.yourssu.model;

import com.yourssu.view.implement.GomokuUIConstants;

public class GomokBoard implements Board {
    private Piece[][] pieces;

    public GomokBoard() {
        this.pieces = new Piece[GomokuUIConstants.BOARD_SIZE][GomokuUIConstants.BOARD_SIZE];
        for (int i = 0; i < GomokuUIConstants.BOARD_SIZE; i++) {
            for (int j = 0; j < GomokuUIConstants.BOARD_SIZE; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }


    @Override
    public Piece getPiece(final int row, final int column) {
        return pieces[row][column];
    }

    @Override
    public void placePiece(final int row, final int column, final Piece piece) {
        if (getPiece(row, column) != Piece.EMPTY) {
            throw new IllegalArgumentException("이미 돌이 있는 자리입니다.");
        }
        pieces[row][column] = piece;
    }
}

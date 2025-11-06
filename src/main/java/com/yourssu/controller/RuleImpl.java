package com.yourssu.controller;

import com.yourssu.model.Board;
import com.yourssu.model.Piece;

public class RuleImpl implements Rule {
    public boolean isWin(int row, int col, Board board) {
        Piece piece = board.getPiece(row, col);
        return true;
    }

    private boolean checkRowWise(int row, int col, Board board, Piece piece) {
        int start = row - 5 < 0 ? 0 : row - 5;
        int end = row + 5 > board.
        return true;
    }

    private boolean checkColWise(int row, int col, Board board) {
        return true;
    }

    private boolean checkDiagonalWise(int diagonal, Board board) {
        return true;
    }
}

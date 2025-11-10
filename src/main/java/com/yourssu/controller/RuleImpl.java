package com.yourssu.controller;

import com.yourssu.model.Board;
import com.yourssu.model.Piece;

public class RuleImpl implements Rule {
    private static final int[] dx = {1, 0, 1, 1};
    private static final int[] dy = {0, 1, 1, -1};

    private Board board;

    public RuleImpl(Board board) {
        this.board = board;
    }

    @Override
    public boolean isWin(int row, int col) {
        for (int dir = 0; dir < 4; dir++) {
            if (countPieceLine(row, col, this.board, dir) >= 5) {
                return true;
            }
        }
        return false;
    }

    private int countPieceLine(int row, int col, Board board, int dir) {
        int count = 0;
        count += countPieceDirection(row, col, board, dx[dir],  dy[dir]);
        count += countPieceDirection(row, col, board, -dx[dir], -dy[dir]);
        return count;
    }

    private int countPieceDirection(int row, int col, Board board, int dx, int dy) {
        int count = 0;
        int curRow = row;
        int curCol = col;
        Piece piece = board.getPiece(row, col);

        while(isMatchingPiece(curRow, curCol, board, piece)) {
            curRow += dx;
            curCol += dy;
            count++;
        }

        return count;
    }

    private boolean isMatchingPiece(int row, int col, Board board, Piece piece) {
        try {
            return piece == board.getPiece(row, col);
        } catch (Exception e) {
            return false;
        }
    }

}
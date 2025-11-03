package com.yourssu.strategy;

import com.yourssu.model.Board;
import com.yourssu.model.Piece;

public class WinnerChecker {
    public WinnerChecker() {
    }

    public boolean checkWinner(Board board, int row, int column) {
        return true;
    }

    private boolean checkRow(Board board, int row, int column) {
        int count = 0;
        Piece pieceColor = board.getPiece(row, column);

        for (int i = column+1; i < board.getSize(); i++) {
            if (board.getPiece(row, i) != pieceColor) {
                break;
            }
            count++;
        }
        for (int i = column; i >= 0; i--) {
            if(board.getPiece(row,i) != pieceColor) {
                break;
            }
            count++;
        }

        if(count == 5) {
            return true;
        }

        return false;
    }

    private boolean checkColumn(Board board, int row, int column) {
        int count = 0;
        Piece pieceColor = board.getPiece(row, column);

        for (int i = column+1; i < board.getSize(); i++) {
            if (board.getPiece(row, i) != pieceColor) {
                break;
            }
            count++;
        }
        for (int i = column; i >= 0; i--) {
            if(board.getPiece(row,i) != pieceColor) {
                break;
            }
            count++;
        }

        if(count == 5) {
            return true;
        }

        return false;
    }
}

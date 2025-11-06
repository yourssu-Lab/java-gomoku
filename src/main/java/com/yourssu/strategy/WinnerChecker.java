package com.yourssu.strategy;

import com.yourssu.model.Board;
import com.yourssu.model.Piece;

public class WinnerChecker {
    public WinnerChecker() {
    }

    public boolean checkWinner(Board board, int row, int column) {

        Piece pieceColor = board.getPiece(row, column);

        if (pieceColor == Piece.EMPTY) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }

        return checkRow(board, row, column) || checkColumn(board, row, column) || checkDiagonal(board, row, column);
    }

    private boolean checkRow(Board board, int row, int column) {
        int count = 0;
        Piece pieceColor = board.getPiece(row, column);

        for (int i = column + 1; i < board.getSize(); i++) {
            if (board.getPiece(row, i) != pieceColor) {
                break;
            }
            count++;
        }
        for (int i = column; i >= 0; i--) {
            if (board.getPiece(row, i) != pieceColor) {
                break;
            }
            count++;
        }

        if (count == 5) {
            return true;
        }

        return false;
    }

    private boolean checkColumn(Board board, int row, int column) {
        int count = 0;
        Piece pieceColor = board.getPiece(row, column);

        for (int i = row + 1; i < board.getSize(); i++) {
            if (board.getPiece(i, column) != pieceColor) {
                break;
            }
            count++;
        }
        for (int i = row; i >= 0; i--) {
            if (board.getPiece(i, column) != pieceColor) {
                break;
            }
            count++;
        }

        if (count == 5) {
            return true;
        }

        return false;
    }

    private boolean checkDiagonal(Board board, int row, int column) {
        return checkDownDiagonal(board, row, column) || checkUpDiagonal(board, row, column);
    }

    private static boolean checkDownDiagonal(Board board, int row, int column) {
        int count = 0;
        Piece pieceColor = board.getPiece(row, column);

        for (int i = 0; i < (board.getSize() + 1) / 2; i++) {
            if (row - i < 0 || column - i < 0) {
                break;
            }
            if (board.getPiece(row - i, column - i) != pieceColor) {
                break;
            }
            count++;
        }

        for (int i = 0; i < (board.getSize() + 1) / 2; i++) {
            if (row + i >= board.getSize() || column + i >= board.getSize()) {
                break;
            }
            if (board.getPiece(row + i, column + i) != pieceColor) {
                break;
            }
            count++;
        }
        return count == 5;
    }

    private static boolean checkUpDiagonal(Board board, int row, int column) {
        int count = 0;
        Piece pieceColor = board.getPiece(row, column);

        for (int i = 0; i < (board.getSize() + 1) / 2; i++) {
            if (row + i >= board.getSize() || column - i < 0) {
                break;
            }
            if (board.getPiece(row + i, column - i) != pieceColor) {
                break;
            }
            count++;
        }

        for (int i = 0; i < (board.getSize() + 1) / 2; i++) {
            if (row - i < 0 || column + i >= board.getSize()) {
                break;
            }
            if (board.getPiece(row - i, column + i) != pieceColor) {
                break;
            }
            count++;
        }
        return count == 5;
    }
}

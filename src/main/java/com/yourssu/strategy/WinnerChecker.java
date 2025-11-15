package com.yourssu.strategy;

import com.yourssu.model.Board;
import com.yourssu.model.Coordinate;
import com.yourssu.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class WinnerChecker {
    public WinnerChecker() {
    }

    public static List<Coordinate> checkWinner(Board board, Coordinate coordinate) {

        Piece pieceColor = board.getPiece(coordinate.row(), coordinate.column());

        if (pieceColor == Piece.EMPTY) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }

        List<Coordinate> checkingLine = checkRow(board, coordinate);
        if (checkingLine != null) {
            return checkingLine;
        }

        checkingLine = checkColumn(board, coordinate);
        if (checkingLine != null) {
            return checkingLine;
        }

        checkingLine = checkDiagonal(board, coordinate);
        if (checkingLine != null) {
            return checkingLine;
        }

        return null;
    }

    private static List<Coordinate> checkRow(Board board, Coordinate coordinate) {
        Piece pieceColor = board.getPiece(coordinate.row(), coordinate.column());
        List<Coordinate> winningLine = new ArrayList<>();

        for (int i = coordinate.column() + 1; i < board.getSize(); i++) {
            if (board.getPiece(coordinate.row(), i) != pieceColor) {
                break;
            }
            winningLine.add(new Coordinate(coordinate.row(), i));
        }
        for (int i = coordinate.column(); i >= 0; i--) {
            if (board.getPiece(coordinate.row(), i) != pieceColor) {
                break;
            }
            winningLine.add(new Coordinate(coordinate.row(), i));

        }

        return checkLine(winningLine);
    }

    private static List<Coordinate> checkColumn(Board board, Coordinate coordinate) {
        Piece pieceColor = board.getPiece(coordinate.row(), coordinate.column());
        List<Coordinate> winningLine = new ArrayList<>();

        for (int i = coordinate.row() + 1; i < board.getSize(); i++) {
            if (board.getPiece(i, coordinate.column()) != pieceColor) {
                break;
            }
            winningLine.add(new Coordinate(i, coordinate.column()));
        }

        for (int i = coordinate.row(); i >= 0; i--) {
            if (board.getPiece(i, coordinate.column()) != pieceColor) {
                break;
            }
            winningLine.add(new Coordinate(i, coordinate.column()));
        }

        return checkLine(winningLine);
    }

    private static List<Coordinate> checkDiagonal(Board board, Coordinate coordinate) {
        List<Coordinate> checkingLine = checkDownDiagonal(board, coordinate);
        if (checkingLine != null) {
            return checkingLine;
        }

        return checkUpDiagonal(board, coordinate);
    }

    private static List<Coordinate> checkDownDiagonal(Board board, Coordinate coordinate) {
        Piece pieceColor = board.getPiece(coordinate.row(), coordinate.column());
        List<Coordinate> winningLine = new ArrayList<>();

        for (int i = 0; i < (board.getSize() + 1) / 2; i++) {
            if (coordinate.row() - i < 0 || coordinate.column() - i < 0) {
                break;
            }
            if (board.getPiece(coordinate.row() - i, coordinate.column() - i) != pieceColor) {
                break;
            }
            winningLine.add(new Coordinate(coordinate.row() - i, coordinate.column() - i));
        }

        for (int i = 0; i < (board.getSize() + 1) / 2; i++) {
            if (coordinate.row() + i >= board.getSize() || coordinate.column() + i >= board.getSize()) {
                break;
            }
            if (board.getPiece(coordinate.row() + i, coordinate.column() + i) != pieceColor) {
                break;
            }
            winningLine.add(new Coordinate(coordinate.row() + i, coordinate.column() + i));

        }
        return checkLine(winningLine);
    }

    private static List<Coordinate> checkUpDiagonal(Board board, Coordinate coordinate) {
        Piece pieceColor = board.getPiece(coordinate.row(), coordinate.column());
        List<Coordinate> winningLine = new ArrayList<>();


        for (int i = 0; i < (board.getSize() + 1) / 2; i++) {
            if (coordinate.row() + i >= board.getSize() || coordinate.column() - i < 0) {
                break;
            }
            if (board.getPiece(coordinate.row() + i, coordinate.column() - i) != pieceColor) {
                break;
            }
            winningLine.add(new Coordinate(coordinate.row() + i, coordinate.column() - i));
        }

        for (int i = 0; i < (board.getSize() + 1) / 2; i++) {
            if (coordinate.row() - i < 0 || coordinate.column() + i >= board.getSize()) {
                break;
            }
            if (board.getPiece(coordinate.row() - i, coordinate.column() + i) != pieceColor) {
                break;
            }
            winningLine.add(new Coordinate(coordinate.row() - i, coordinate.column() + i));

        }

        return checkLine(winningLine);
    }

    private static List<Coordinate> checkLine(List<Coordinate> winningLine) {
        if (winningLine.size() == 5) {
            return winningLine;
        }

        return null;
    }
}

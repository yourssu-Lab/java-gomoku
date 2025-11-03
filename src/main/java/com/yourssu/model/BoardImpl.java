package com.yourssu.model;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    private final List<List<Piece>> board;

    public BoardImpl(int size) {
        board = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                board.get(i).add(Piece.EMPTY);
            }
        }
    }

    @Override
    public Piece getPiece(int row, int column) {
        return board.get(row).get(column);
    }

    @Override
    public void placePiece(int row, int column, Piece piece) {
        board.get(row).set(column, piece);
    }

    @Override
    public int getSize() {
        return board.size();
    }
}

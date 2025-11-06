package com.yourssu.model;

public interface Board {
    Piece getPiece(int row, int column);
    void placePiece(int row, int column, Piece piece);
    int getSize();
}

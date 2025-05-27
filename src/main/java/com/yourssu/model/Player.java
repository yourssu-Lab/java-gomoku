package com.yourssu.model;

import com.yourssu.view.implement.Symbol;

public interface Player {
    Piece getPiece();
    void placePiece(int row, int column);
}

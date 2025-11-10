package com.yourssu.controller;

import com.yourssu.model.Board;

public interface Rule {
    boolean isWin(int row, int col);
}

package com.yourssu.console.controller;

import com.yourssu.domain.Piece;

@FunctionalInterface
public interface Controller {
    Piece run();
}

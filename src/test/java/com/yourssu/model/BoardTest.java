package com.yourssu.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BoardTest {
    @Test
    void 특정행_열의값을_출력한다() {
        Board board = new BoardImpl(5);

        assertThat(board.getPiece(2,3))
                .isEqualTo(Piece.EMPTY);
    }

    @Test
    void placePiece함수는_특정행_열의_값을_바꾼다() {
        Board board = new BoardImpl(5);

        board.placePiece(2,3, Piece.BLACK);
        assertThat(board.getPiece(2,3))
                .isEqualTo(Piece.BLACK);
    }
}
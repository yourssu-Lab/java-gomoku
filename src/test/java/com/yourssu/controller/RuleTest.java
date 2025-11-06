package com.yourssu.controller;

import com.yourssu.model.Board;
import com.yourssu.model.BoardImpl;
import com.yourssu.model.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RuleTest {
    @Test
    void 보드의_가로의_돌_개수_5개() {
        // given
        Piece[][] piece = {
                {Piece.WHITE, Piece.WHITE, Piece.WHITE, Piece.WHITE, Piece.WHITE},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY}
        };

        Board board = new BoardImpl(piece);
        // when

        Rule currentRule = new RuleImpl();

        assertThat(currentRule.isWin(0, 0, board)).isEqualTo(true);
    }
}

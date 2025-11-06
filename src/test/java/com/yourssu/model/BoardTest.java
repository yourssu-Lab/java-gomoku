package com.yourssu.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BoardTest {
    @Test
    void getPieceGetWellState() {
        // given
      Piece[][] boardState = new Piece[5][5];
      boardState[3][3] = Piece.WHITE;



        // when
      Board board = new BoardImpl(boardState);
      Piece result = board.getPiece(3, 3);

      // then
      assertThat(result).isNotNull().isInstanceOf(Piece.class).isEqualTo(Piece.WHITE);
    }

    @Test
    void whenGetPieceGetIllegalArgument() {
        Board board = new BoardImpl(10);
        assertThatThrownBy(() -> board.getPiece(20,20)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> board.getPiece(-5,-5)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPlacePieceGetIllegalArgument() {
        Board board = new BoardImpl(10);
        assertThatThrownBy(() -> board.placePiece(20,20, Piece.WHITE)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> board.placePiece(-5,-5, Piece.WHITE)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isBoardInitializedByEmpty() {
        Board board = new BoardImpl(5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertThat(board.getPiece(i, j)).isNotNull().isInstanceOf(Piece.class).isEqualTo(Piece.EMPTY);
            }
        }
    }

    @Test
    void whenBoardGetIllegalSize() {
        assertThatThrownBy(() -> new BoardImpl(20)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BoardImpl(1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isPlacePieceWellMutateState() {
        // given
        Piece[][] boardState = new Piece[5][5];

        Board board = new BoardImpl(boardState);
        board.placePiece(0,0,Piece.WHITE);

        assertThat(boardState[0][0]).isNotNull().isInstanceOf(Piece.class).isEqualTo(Piece.WHITE);
    }

    @Test
    void isPlacePieceMutateChangedState() {
        // given
        Piece[][] boardState = new Piece[5][5];

        Board board = new BoardImpl(boardState);
        board.placePiece(0,0,Piece.WHITE);

        assertThat(boardState[0][0]).isNotNull().isInstanceOf(Piece.class).isEqualTo(Piece.WHITE);
    }
}

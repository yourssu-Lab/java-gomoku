package com.yourssu.strategy;


import com.yourssu.model.Board;
import com.yourssu.model.BoardImpl;
import com.yourssu.model.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinnerCheckerTest {

    @Test
    void 가로_우승에_대해_입력된_보드_상태가_승이면_true를_반환한다() {
        // given
        Board board = new BoardImpl(5);
        WinnerChecker winnerChecker = new WinnerChecker();
        for(int i = 0; i < 5; i++) {
            board.placePiece(i,2, Piece.BLACK);
        }

        // when
        int row = 4, column = 2;
        boolean isWinner = winnerChecker.checkWinner(board, row, column);

        assertThat(isWinner).isEqualTo(true);
    }

    @Test
    void 입력된_보드_상태가_진행중이면_false를_반환한다() {
        // given
        Board board = new BoardImpl(5);
        WinnerChecker winnerChecker = new WinnerChecker();

        board.placePiece(4,2, Piece.WHITE);

        int row = 4, column = 2;
        boolean isWinner = winnerChecker.checkWinner(board, row, column);

        assertThat(isWinner).isEqualTo(false);
    }

    @Test
    void 세로_우승에_대해_입력된_보드_상태가_승이면_true를_반환한다() {
        // given
        Board board = new BoardImpl(5);
        WinnerChecker winnerChecker = new WinnerChecker();
        for(int i = 0; i < 5; i++) {
            board.placePiece(1, i, Piece.BLACK);
        }

        // when
        int row = 1, column = 2;
        boolean isWinner = winnerChecker.checkWinner(board, row, column);

        assertThat(isWinner).isEqualTo(true);
    }

    @Test
    void 우하향_대각선_우승에_대해_입력된_보드_상태가_승이면_true를_반환한다() {
        // given
        Board board = new BoardImpl(5);
        WinnerChecker winnerChecker = new WinnerChecker();
        for(int i = 0; i < 5; i++) {
            board.placePiece(i, i, Piece.BLACK);
        }

        // when
        int row = 1, column = 1;
        boolean isWinner = winnerChecker.checkWinner(board, row, column);

        assertThat(isWinner).isEqualTo(true);
    }

    @Test
    void 우상향_대각선_우승에_대해_입력된_보드_상태가_승이면_true를_반환한다() {
        // given
        Board board = new BoardImpl(5);
        WinnerChecker winnerChecker = new WinnerChecker();
        for(int i = 0; i < 5; i++) {
            board.placePiece(4-i, i, Piece.BLACK);
        }
        // when
        int row = 1, column = 3;
        boolean isWinner = winnerChecker.checkWinner(board, row, column);

        assertThat(isWinner).isEqualTo(true);
    }

    @Test
    void 마지막에_두었다고_하는_위치가_EMPTY이면_에러를_반환한다() {
        // given
        Board board = new BoardImpl(5);
        WinnerChecker winnerChecker = new WinnerChecker();
        for(int i = 0; i < 5; i++) {
            board.placePiece(1, i, Piece.BLACK);
        }
        // when and then
        int row = 2, column = 3;
        assertThatThrownBy(() -> {winnerChecker.checkWinner(board, row, column);})
                .isInstanceOf(IllegalArgumentException.class);
    }
}

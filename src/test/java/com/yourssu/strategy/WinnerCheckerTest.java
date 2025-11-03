package com.yourssu.strategy;


import com.yourssu.model.Board;
import com.yourssu.model.BoardImpl;
import com.yourssu.model.Piece;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinnerCheckerTest {

    @Test
    void 입력된_보드_상태가_승이면_true를_반환한다() {
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
}
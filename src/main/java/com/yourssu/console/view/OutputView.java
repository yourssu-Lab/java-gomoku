package com.yourssu.console.view;

import com.yourssu.domain.Board;
import com.yourssu.domain.Piece;
import com.yourssu.console.view.implement.Symbol;

public interface OutputView {
    void printTurn(int turn);
    void printBoard(Board board);
    void printWinner(Board board, Symbol winner);
    void printGameOverMessage();
    void printCurrentPlayer(Piece currentPlayer);
}

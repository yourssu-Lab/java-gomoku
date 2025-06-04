package com.yourssu.view;

import com.yourssu.model.Board;
import com.yourssu.model.Piece;
import com.yourssu.view.implement.Symbol;

public interface OutputView {
    void printTurn(int turn);
    void printBoard(Board board);
    void printWinner(Board board, Symbol winner);
    void printGameOverMessage();
    void printCurrentPlayer(Piece currentPlayer);
    void printDrawMessage();
}

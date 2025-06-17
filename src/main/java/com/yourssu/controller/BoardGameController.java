package com.yourssu.controller;

import com.yourssu.model.*;
import com.yourssu.view.InputView;
import com.yourssu.view.OutputView;
import com.yourssu.view.dto.CoordinateDTO;
import com.yourssu.view.implement.ConsoleInputView;
import com.yourssu.view.implement.ConsoleOutputView;
import com.yourssu.view.implement.Symbol;

public class BoardGameController implements Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Integer turn;
    private CoordinateDTO coord;

    public BoardGameController() {
        inputView = new ConsoleInputView();
        outputView = new ConsoleOutputView();
        board = new GomokuBoard(15);
        player1 = new GomokPlayer(Piece.BLACK);
        player2 = new GomokPlayer(Piece.WHITE);
        turn = 0;
    }

    @Override
    public void run() {
        while (true) {
            turn++;
            printView(player1);
            if (setPiece(player1) == false) {
                break;
            }
            if (isGameEnd(player1)) {
                break;
            }

            printView(player2);
            if (setPiece(player2) == false) {
                break;
            }
            if (isGameEnd(player2)) {
                break;
            }
        }
    }

    private void printView(Player player) {
        outputView.printTurn(turn);
        outputView.printBoard(board);
        outputView.printCurrentPlayer(player.getPiece());
    }

    private boolean setPiece(Player player) {
        coord = inputView.getInputForCoordinate();
        if (coord == null) {
            return false;
        }
        board.placePiece(coord.row(), coord.column(), player.getPiece());
        return true;
    }

    private boolean isGameEnd(Player player) {
        if (board.isGameEnd()) {
            outputView.printWinner(board, Symbol.of(player.getPiece()));
            return true;
        }
        return false;
    }
}

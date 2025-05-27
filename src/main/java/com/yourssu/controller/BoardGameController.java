package com.yourssu.controller;

import com.yourssu.model.*;
import com.yourssu.view.InputView;
import com.yourssu.view.OutputView;
import com.yourssu.view.dto.CoordinateDTO;
import com.yourssu.view.implement.ConsoleInputView;
import com.yourssu.view.implement.ConsoleOutputView;

public class BoardGameController implements Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Integer turn;

    public BoardGameController() {
        inputView = new ConsoleInputView();
        outputView = new ConsoleOutputView();
        board = new GomokuBoard(15);
        player1 = new GomokPlayer(Piece.BLACK, board);
        player2 = new GomokPlayer(Piece.WHITE, board);
        turn = 0;
    }

    @Override
    public void run() {
        CoordinateDTO coord;
        while (true) {
            turn++;
            outputView.printTurn(turn);
            outputView.printBoard(board);
            outputView.printCurrentPlayer(player1.getPiece());
            coord = inputView.getInputForCoordinate();
            if (coord == null) {
                break;
            }
            board.placePiece(coord.row(), coord.column(), player1.getPiece());

            outputView.printTurn(turn);
            outputView.printBoard(board);
            outputView.printCurrentPlayer(player2.getPiece());
            coord = inputView.getInputForCoordinate();
            if (coord == null) {
                break;
            }
            board.placePiece(coord.row(), coord.column(), player2.getPiece());
        }
    }
}

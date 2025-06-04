package com.yourssu.controller;

import com.yourssu.model.Board;
import com.yourssu.model.Piece;
import com.yourssu.model.VictoryCondition;
import com.yourssu.view.InputView;
import com.yourssu.view.OutputView;
import com.yourssu.view.dto.CoordinateDTO;
import com.yourssu.view.implement.Symbol;

public class BoardGameController implements Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Board board;
    private final VictoryCondition victoryCondition;

    private Piece currentPiece = Piece.BLACK;
    private int turn = 1;

    public BoardGameController(final InputView inputView, final OutputView outputView, final Board board) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.board = board;
        this.victoryCondition = new VictoryCondition(this.board);
    }

    @Override
    public void run() {

        while (true) {
            outputView.printTurn(turn);
            outputView.printBoard(board);
            outputView.printCurrentPlayer(currentPiece);
            CoordinateDTO coordinate = inputView.getInputForCoordinate();

            if (coordinate == null) {
                outputView.printGameOverMessage();
                return;
            }
            try {
                board.placePiece(coordinate.row(), coordinate.column(), this.currentPiece);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (victoryCondition.isWin(coordinate.row(), coordinate.column())) {
                outputView.printWinner(board, Symbol.of(currentPiece));
                return;
            }

            if(victoryCondition.isDraw(turn)) {
                outputView.printDrawMessage();
                return;
            }

            togglePiece();

        }
    }

    private void togglePiece() {
        if (this.currentPiece == Piece.BLACK) {
            this.currentPiece = Piece.WHITE;
        } else if (this.currentPiece == Piece.WHITE) {
            this.currentPiece = Piece.BLACK;
            turn++;
        }
    }
}

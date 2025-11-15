package com.yourssu.console.controller;

import com.yourssu.domain.Board;
import com.yourssu.domain.Piece;
import com.yourssu.console.view.InputView;
import com.yourssu.console.view.OutputView;
import com.yourssu.console.view.dto.CoordinateDTO;
import com.yourssu.console.view.implement.Symbol;
import com.yourssu.domain.Rule;
import com.yourssu.domain.RuleImpl;

import static java.lang.Math.pow;

public class ControllerImpl implements Controller {
    private int turn = 1;

    private final OutputView outputView;
    private final InputView inputView;

    private final Board board;
    private final Rule rule;

    public ControllerImpl(OutputView outputView, InputView inputView, Board board) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.board =  board;
        this.rule = new RuleImpl(board);
    }

    @Override
    public Piece run() {
        Piece currentPiece = null;

        while(this.turn != pow(board.getSize(), 2)) {
            if (this.turn % 2 == 1) {
                currentPiece = Piece.BLACK;
            } else {
                currentPiece = Piece.WHITE;
            }

            if (proceedTurn(currentPiece)) {
                printGameOver(currentPiece);
                break;
            }

            turn++;
        }

        if (this.turn == pow(board.getSize(), 2)) {
            return null;
        }

        return currentPiece;
    }

    private boolean proceedTurn(Piece currentPlayer) {
        printPrompt(currentPlayer);

        CoordinateDTO pos = this.inputView.getInputForCoordinate();
        this.board.placePiece(pos.row(), pos.column(), currentPlayer);

        return rule.isWin(pos.row(), pos.column());
    }

    private void printPrompt(Piece currentPlayer) {
        outputView.printTurn((this.turn / 2) + 1);
        outputView.printBoard(this.board);
        outputView.printCurrentPlayer(currentPlayer);
    }

    private void printGameOver(Piece currentPlayer) {
        outputView.printWinner(board, Symbol.of(currentPlayer));
        outputView.printGameOverMessage();
    }
}

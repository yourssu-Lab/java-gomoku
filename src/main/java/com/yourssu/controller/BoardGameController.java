package com.yourssu.controller;

import com.yourssu.model.Board;
import com.yourssu.model.GameCondition;
import com.yourssu.model.GameConditionDTO;
import com.yourssu.model.Piece;
import com.yourssu.model.GameConditionManager;
import com.yourssu.view.InputView;
import com.yourssu.view.OutputView;
import com.yourssu.view.dto.CoordinateDTO;
import com.yourssu.view.implement.Symbol;

public class BoardGameController implements Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Board board;
    private final GameConditionManager gameConditionManager;

    private Piece currentPiece = Piece.BLACK;
    private int turn = 1;

    public BoardGameController(final InputView inputView, final OutputView outputView, final Board board) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.board = board;
        this.gameConditionManager = new GameConditionManager(this.board);
    }

    @Override
    public void run() {
        while (true) {

            printTurnBoardPlayer();
            CoordinateDTO coordinate = inputView.getInputForCoordinate();

            if (coordinate == null) {
                outputView.printGameOverMessage();
                return;
            }

            GameConditionDTO gameConditionDTO = new GameConditionDTO(coordinate.row(), coordinate.column(), turn, this.currentPiece);
            
            GameCondition gameCondition = gameConditionManager.judgeGameCondition(gameConditionDTO);

            if (gameCondition == GameCondition.INVALID) {
                outputView.printInvalidPlaceMessage();
                continue;
            }
            if (gameCondition == GameCondition.WIN) {
                outputView.printWinner(board, Symbol.of(currentPiece));
                return;
            }
            if (gameCondition == GameCondition.DRAW) {
                outputView.printDrawMessage();
                return;
            }
            togglePiece();
        }
    }

    private void printTurnBoardPlayer() {
        outputView.printTurn(turn);
        outputView.printBoard(board);
        outputView.printCurrentPlayer(currentPiece);
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

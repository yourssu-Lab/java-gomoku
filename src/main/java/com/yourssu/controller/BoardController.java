package com.yourssu.controller;

import com.yourssu.model.Board;
import com.yourssu.model.BoardImpl;
import com.yourssu.model.Turn;
import com.yourssu.strategy.WinnerChecker;
import com.yourssu.view.InputView;
import com.yourssu.view.OutputView;
import com.yourssu.view.dto.CoordinateDTO;
import com.yourssu.view.implement.Symbol;

public class BoardController implements Controller {

    private InputView inputView;
    private OutputView outputView;
    private Turn turn;
    private WinnerChecker winnerChecker;
    private Board board;

    public BoardController(InputView consoleInputView, OutputView consoleOutputView, Turn turn, WinnerChecker winnerChecker, BoardImpl board) {
        this.inputView = consoleInputView;
        this.outputView = consoleOutputView;
        this.turn = turn;
        this.winnerChecker = winnerChecker;
        this.board = board;
    }

    @Override
    public void run() {

        boolean isCompleted =true;

        while (true) {

            outputView.printTurn(turn.getCurrentTurn());
            outputView.printBoard(board);
            outputView.printCurrentPlayer(turn.getCurrnetPiece());

            CoordinateDTO coordinateDTO = inputView.getInputForCoordinate(board.getSize());

            if (coordinateDTO == null) {
                System.out.println("게임이 종료되었습니다.");
                isCompleted = false;
                break;
            }

            board.placePiece(coordinateDTO.row(), coordinateDTO.column(), turn.getCurrnetPiece());

            if (winnerChecker.checkWinner(board, CoordinateDTO.toDomain(coordinateDTO)) != null){
                break;
            }

            turn.next();
        }

        if(isCompleted) {
            outputView.printWinner(board, Symbol.of(turn.getCurrnetPiece()));
        }
    }
}

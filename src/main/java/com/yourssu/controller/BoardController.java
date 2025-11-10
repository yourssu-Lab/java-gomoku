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

        while (true) {
            outputView.printTurn(turn.getCurrentTurn());
            outputView.printBoard(board);
            outputView.printCurrentPlayer(turn.getCurrnetPiece());

            CoordinateDTO coordinateDTO = inputView.getInputForCoordinate(board.getSize());

            board.placePiece(coordinateDTO.row(), coordinateDTO.column(), turn.getCurrnetPiece());

            if (winnerChecker.checkWinner(board, coordinateDTO.row(), coordinateDTO.column())){
                break;
            }

            turn.next();
        }

        outputView.printWinner(board, Symbol.of(turn.getCurrnetPiece()));
    }


    /*
    턴 몇인지 : Turn & outputVIew
    보드 현상태 : outputView
    누구차례입니다~ 입력하세요 : turn & outputView

    input => A7  : input View
    board.setPiece()
    지금이 승리 상태인지 확인
   아니면 다음턴 넘기고
   출력 반복.

   승인경우: printWinner : OutputView

     */
}

package com.yourssu.view.implement;

import com.yourssu.model.Board;
import com.yourssu.model.Piece;
import com.yourssu.view.OutputView;

import static com.yourssu.view.implement.GomokuUIConstants.*;

public class ConsoleOutputView implements OutputView {
    @Override
    public void printTurn(int turn) {
        System.out.println("\nTurn " + turn);
    }

    @Override
    public void printBoard(Board board) {
        printColumnLabels();
        printRows(board);
        printColumnLabels();
    }

    private void printColumnLabels() {
        System.out.print(" O ");
        for (int c = 0; c < BOARD_SIZE; c++) {
            System.out.print(" " + COLUMN_LABELS.charAt(c));
        }
        System.out.println();
    }

    private void printRows(Board board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            printRow(board, row);
        }
    }

    private static void printRow(Board board, int row) {
        System.out.printf("%2d ", BOARD_SIZE - row);
        for (int column = 0; column < BOARD_SIZE; column++) {
            System.out.print(" " + Symbol.of(board.getPiece(row, column)).getString());
        }
        System.out.println();
    }

    @Override
    public void printCurrentPlayer(Piece currentPlayer) {
        if (!PLAYER_PROMPTS.containsKey(currentPlayer)) {
            throw new IllegalArgumentException();
        }
        System.out.print(PLAYER_PROMPTS.get(currentPlayer));
    }

    @Override
    public void printWinner(Board board, Symbol winner) {
        System.out.println("\nResult");
        printBoard(board);
        System.out.printf("%s wins!\n", winner.name());
    }

    @Override
    public void printGameOverMessage() {
        System.out.println("\n게임이 종료되었습니다.");
    }

    @Override
    public void printDrawMessage() {
        System.out.println("\n무승부로 게임이 종료되었습니다.");
    }
    @Override
    public void printInvalidPlaceMessage(){System.out.println("\n이미 돌이 착수된 자리입니다.");}

}

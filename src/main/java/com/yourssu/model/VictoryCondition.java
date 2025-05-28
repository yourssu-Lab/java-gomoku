package com.yourssu.model;

public class VictoryCondition {
    private final Board board;

    public VictoryCondition(final Board board) {
        this.board = board;
    }

    public boolean isWin(int row, int column) {
        Piece piece = board.getPiece(row, column);
        // TODO: 승리 조건 알고리즘 생각해오기
        return true;
    }

    public boolean isDraw(int turn){
        // TODO: 무승부 조건 수정해야 함.
        return turn == 255;
    }
}

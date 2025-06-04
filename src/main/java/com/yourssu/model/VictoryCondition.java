package com.yourssu.model;

import com.yourssu.view.implement.GomokuUIConstants;

import java.util.concurrent.Delayed;

public class VictoryCondition {
    private final Board board;
    private static final int WIN_COUNT = 5;

    private final int[][] directions = {{0,1},{1,0},{1,1},{1,-1}};

    private final int endTurnCount = GomokuUIConstants.BOARD_SIZE * GomokuUIConstants.BOARD_SIZE / 2 + 1;

    public VictoryCondition(final Board board) {
        this.board = board;
    }

    public boolean isWin(int row, int column) {
        Piece piece = board.getPiece(row, column);
        if (piece==Piece.EMPTY){throw new IllegalArgumentException("돌이 착수되지 않았습니다.");}

        for (final int[] direction : directions) {
            int count = 1;
            int deltaRow = direction[0];
            int deltaColumn = direction[1];
            for (int dir = -1; dir <= 1; dir+=2) {
                int currentRow = row+(deltaRow*dir);
                int currentColumn = column+(deltaColumn*dir);

                while(currentRow >= 0 && currentRow < GomokuUIConstants.BOARD_SIZE &&
                        currentColumn >= 0 && currentColumn < GomokuUIConstants.BOARD_SIZE &&
                        board.getPiece(currentRow, currentColumn) == piece) {
                    count++;
                    currentRow += deltaRow*dir;
                    currentColumn += deltaColumn*dir;
                }
            }

            if(count >= WIN_COUNT) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw(int turn){
        return turn >= endTurnCount;
    }
}

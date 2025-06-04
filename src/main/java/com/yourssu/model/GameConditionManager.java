package com.yourssu.model;

import com.yourssu.view.implement.GomokuUIConstants;

public class GameConditionManager {
    private final Board board;
    private static final int WIN_COUNT = 5;

    private final int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

    private final int endTurnCount = GomokuUIConstants.BOARD_SIZE * GomokuUIConstants.BOARD_SIZE / 2 + 1;

    public GameConditionManager(final Board board) {
        this.board = board;
    }

    public GameCondition judgeGameCondition(GameConditionDTO gameConditionDTO) {
        int row = gameConditionDTO.row();
        int column = gameConditionDTO.column();

        try {
            board.placePiece(row, column, gameConditionDTO.piece());
        } catch (IllegalArgumentException e) {
            return GameCondition.INVALID;
        }

        if (isWin(row, column)) {
            return GameCondition.WIN;
        }

        if (isDraw(gameConditionDTO.turn())) {
            return GameCondition.DRAW;
        }

        return GameCondition.VALID;
    }

    private boolean isWin(int row, int column) {
        Piece piece = board.getPiece(row, column);
        if (piece == Piece.EMPTY) {
            throw new IllegalArgumentException("돌이 착수되지 않았습니다.");
        }

        for (final int[] direction : directions) {
            final int count = getCount(row, column, direction, piece);

            if (count >= WIN_COUNT) {
                return true;
            }
        }
        return false;
    }

    private int getCount(final int row, final int column, final int[] direction, final Piece piece) {
        int count = 1;
        int deltaRow = direction[0];
        int deltaColumn = direction[1];
        for (int dir = -1; dir <= 1; dir += 2) {
            int currentRow = row + (deltaRow * dir);
            int currentColumn = column + (deltaColumn * dir);

            while (isValidBound(currentRow, currentColumn) &&
                    board.getPiece(currentRow, currentColumn) == piece
            ) {
                count++;
                currentRow += deltaRow * dir;
                currentColumn += deltaColumn * dir;
            }
        }

        return count;
    }

    private boolean isValidBound(final int currentRow, final int currentColumn) {
        return currentRow >= 0 && currentRow < GomokuUIConstants.BOARD_SIZE &&
                currentColumn >= 0 && currentColumn < GomokuUIConstants.BOARD_SIZE;
    }

    private boolean isDraw(int turn) {
        return turn >= endTurnCount;
    }
}

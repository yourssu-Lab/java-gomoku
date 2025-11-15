package com.yourssu.view.implement;

import com.yourssu.model.*;
import com.yourssu.view.dto.*;

import java.time.LocalDateTime;

public class OutputMapper {

    public static CreateGameResponse createGame(Game game) {
        return new CreateGameResponse(
                game.getId().toString(),
                game.getBoard().getSize(),
                GameStatus.IN_PROGRESS,
                game.getTurn().toString(),
                boardToString(game.getBoard()),
                LocalDateTime.now()
        );
    }

    public static GetGameResponse getGame(Game game) {
        Board board = game.getBoard();
        Turn turn = game.getTurn();
        MoveForGet move = new MoveForGet(
                turn.getCurrnetPiece().name(),
                CoordinateDTO.from(game.getPlayedCoordinate()),
                turn.getCurrentTurn());

        return new GetGameResponse(
                game.getId().toString(),
                board.getSize(),
                GameStatus.IN_PROGRESS,
                turn.getCurrnetPiece(),
                game.getWinner().name(),
                boardToString(board),
                turn.getCurrentTurn(),
                move,
                game.getCreatedAt(),
                game.getUpdateAt()
        );
    }

    public static PlaceStoneResponse toPlaceStoneResponse(Game game) {
        Board board = game.getBoard();
        Turn turn = game.getTurn();

        return PlaceStoneResponse.of(
                turn.getCurrnetPiece().name(),
                CoordinateDTO.from(game.getPlayedCoordinate()),
                turn.getCurrentTurn(),
                game.getUpdateAt(),
                game.getId().toString(),
                board.getSize(),
                game.getStatus(),
                turn.getCurrnetPiece().name(),
                game.getWinner().name(),
                game.getWinningLine().stream().map(CoordinateDTO::from).toArray(CoordinateDTO[]::new),
                boardToString(board),
                turn.getCurrentTurn(),
                game.getUpdateAt()
        );
    }


    private static String[] boardToString(Board board) {
        String[] boardString = new String[board.getSize()];

        for (int i = 0; i < board.getSize(); i++) {
            boardString[i] = getRow(board, i);
        }

        return boardString;
    }

    private static String getRow(Board board, int rowIndex) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < board.getSize(); i++) {
            row.append(Symbol.of(board.getPiece(i, rowIndex)));
        }

        return row.toString();
    }
}

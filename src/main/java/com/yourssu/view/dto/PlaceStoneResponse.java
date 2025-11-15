package com.yourssu.view.dto;

import com.yourssu.model.GameStatus;

import java.time.LocalDateTime;

public record PlaceStoneResponse(
        Move move,
        Game game
) {
    private record Move(
            String player,
            CoordinateDTO coordinate,
            Integer turnNumber,
            LocalDateTime placedAt
    ){}

    private record Game(
            String gameId,
            Integer size,
            GameStatus status,
            String currentTurn,
            String winner,
            CoordinateDTO[] winningLine,
            String[] board,
            Integer turnCount,
            LocalDateTime updatedAt
    ) {}

    public static PlaceStoneResponse of(
            String player,
            CoordinateDTO coordinate,
            Integer turnNumber,
            LocalDateTime placedAt,
            String gameId,
            Integer size,
            GameStatus status,
            String currentTurn,
            String winner,
            CoordinateDTO[] winningLine,
            String[] board,
            Integer turnCount,
            LocalDateTime updatedAt
    ) {
        return new PlaceStoneResponse(
                new Move(player, coordinate, turnNumber, placedAt),
                new Game(gameId, size, status, currentTurn, winner, winningLine, board, turnCount, updatedAt)
        );
    }
}

package com.yourssu.view.dto;

import com.yourssu.model.GameStatus;
import com.yourssu.model.Piece;

import java.time.LocalDateTime;

public record GetGameResponse(
        String gameId,
        Integer size,
        GameStatus status,
        Piece currentTurn,
        String winner,
        String[] board,
        Integer turnCount,
        MoveForGet lastMove,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

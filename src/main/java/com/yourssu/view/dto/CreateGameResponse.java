package com.yourssu.view.dto;

import com.yourssu.model.GameStatus;

import java.time.LocalDateTime;

public record CreateGameResponse(
        String gameId,
        Integer size,
        GameStatus status,
        String currentTurn,
        String[] board,
        LocalDateTime createdAt
){
}

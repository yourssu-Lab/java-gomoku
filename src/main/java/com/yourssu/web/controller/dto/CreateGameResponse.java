package com.yourssu.web.controller.dto;

import com.yourssu.domain.Status;
import com.yourssu.domain.Piece;

import java.util.UUID;
import java.time.ZonedDateTime;

public record CreateGameResponse(
        UUID gameId,
        int size,
        Status status,
        Piece currentTurn,
        String[] board,
        ZonedDateTime createdAt
) {
}

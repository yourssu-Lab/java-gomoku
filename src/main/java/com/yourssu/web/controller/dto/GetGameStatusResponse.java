package com.yourssu.web.controller.dto;

import com.yourssu.domain.Status;
import com.yourssu.domain.Piece;
import com.yourssu.domain.Board;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record GetGameStatusResponse(
        @NotBlank UUID gameId,
        @NotNull int size,
        @NotNull Status status,

        Piece currentTurn,
        Piece winner,

        @NotNull String[] board,
        @PositiveOrZero int turnCount,
        @Valid Move lastmove,
        @NotNull String createdAt,
        @NotNull String updatedAt
) {
}

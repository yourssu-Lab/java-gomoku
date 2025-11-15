package com.yourssu.web.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record Game(
        @NotBlank String gameId,
        @NotNull int size,
        @NotNull String status,

        String currentTurn,
        String winner,
        List<@Valid Coordinate> winningLine,

        @NotNull String[] board,
        @PositiveOrZero int turnCount,
        @NotNull String updatedAt

) {

}

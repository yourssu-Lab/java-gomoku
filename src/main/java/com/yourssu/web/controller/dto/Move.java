package com.yourssu.web.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Move(
        @NotNull
        String player,

        @NotNull
        @Valid
        Coordinate coordinate,

        @Positive
        int turnNumber
) {

}

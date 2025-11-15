package com.yourssu.web.controller.dto;

import jakarta.validation.Valid;

public record PlaceStoneResponse(
        @Valid Move move,
        @Valid Game game
) {
    public PlaceStoneResponse() {
    }
}

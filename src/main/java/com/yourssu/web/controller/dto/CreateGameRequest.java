package com.yourssu.web.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record CreateGameRequest(
        @Min(value = 5)
        @Max(value = 15)
        Integer size
) {
    private static final int DEFAULT_SIZE = 15;

    public CreateGameRequest {
        if (size == null) {
            size = DEFAULT_SIZE;
        }
    }
}
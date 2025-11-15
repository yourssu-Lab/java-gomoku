package com.yourssu.view.dto;

import java.time.LocalDateTime;

public record MoveForPlace(
        String player,
        CoordinateDTO coordinate,
        int turnNumber,
        LocalDateTime placedAt
) {
}

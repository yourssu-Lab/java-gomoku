package com.yourssu.domain;

import java.time.ZonedDateTime;

public record Move (
        Piece player,
        Coordinate coordinate,
        int turnNumber,
        ZonedDateTime placedAt
) {
}

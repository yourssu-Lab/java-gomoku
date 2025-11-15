package com.yourssu.web.controller.dto;

import jakarta.validation.Valid;

public record PlaceStoneRequest(
        @Valid  Coordinate coordinate
) {
}

package com.yourssu.view.dto;

public record MoveForGet(
    String player,
    CoordinateDTO coordinate,
    int turnNumber
) {
}
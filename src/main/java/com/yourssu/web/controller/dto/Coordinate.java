package com.yourssu.web.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Coordinate(
        @NotBlank()
        @Pattern(regexp = "^[A-O]$")
        String row,

        @Min(value = 1)
        @Max(value = 15)
        int column
) {

}

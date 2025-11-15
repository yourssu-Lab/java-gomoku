package com.yourssu.view.dto;

import org.hibernate.validator.constraints.Range;

public record CreateGameRequest(
        @Range(min = 5, max = 15)
        Integer size
) {
}

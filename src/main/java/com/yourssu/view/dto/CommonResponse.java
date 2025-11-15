package com.yourssu.view.dto;

import java.time.LocalDateTime;

public record CommonResponse<T>(
        LocalDateTime timestamp,
        T result
){
    public static <T> CommonResponse<T> of(T result){
        return new CommonResponse<>(LocalDateTime.now(), result);
    }
}

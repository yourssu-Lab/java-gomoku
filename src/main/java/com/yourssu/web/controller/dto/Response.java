package com.yourssu.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

public class Response {
    @Getter
    @AllArgsConstructor
    public static class SuccessBody<T> {
        @JsonProperty
        private final ZonedDateTime timestamp;

        @JsonProperty
        private final T result;
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorBody {
        @JsonProperty
        private final ZonedDateTime timestamp;

        @JsonProperty
        private final String error;
    }

    public static <T> ResponseEntity<SuccessBody<T>> ok(T result) {
        return ResponseEntity.ok(
                new SuccessBody<>(ZonedDateTime.now(), result)
        );
    }

    public static <T> ResponseEntity<SuccessBody<T>> created(T result) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessBody<>(ZonedDateTime.now(), result));
    }

    public static ResponseEntity<Void> noContent() {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    // === 실패 응답 메서드 ===
    public static ResponseEntity<ErrorBody> error(String message) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorBody(ZonedDateTime.now(), message));
    }

    public static ResponseEntity<ErrorBody> notFound(String message) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorBody(ZonedDateTime.now(), message));
    }

    public static ResponseEntity<ErrorBody> badRequest(String message) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorBody(ZonedDateTime.now(), message));
    }

    public static ResponseEntity<ErrorBody> unauthorized(String message) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorBody(ZonedDateTime.now(), message));
    }

    public static ResponseEntity<ErrorBody> forbidden(String message) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorBody(ZonedDateTime.now(), message));
    }

    public static ResponseEntity<ErrorBody> conflict(String message) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorBody(ZonedDateTime.now(), message));
    }
}
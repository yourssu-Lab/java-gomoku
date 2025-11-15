package com.yourssu.domain;

import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class Game {
    private final UUID gameId;

    private Status status;

    private Piece currentTurn;

    private int turnCount;

    private Piece winner;

    private Coordinate[] winningLine;

    private ZonedDateTime createdAt;

    public Game() {
        this.gameId = UUID.randomUUID();
        this.status = Status.IN_PROGRESS;
        this.currentTurn = Piece.BLACK;
        this.winner = null;
        this.turnCount = 1;
    }

    public Status setStatus(Status status) {
        this.status = status;
        return this.status;
    }

    public Piece processTurn() {
        if (this.currentTurn == Piece.BLACK) {
            this.currentTurn = Piece.WHITE;
        } else {
            this.currentTurn = Piece.BLACK;
        }
        return this.currentTurn;
    }

    public int nextTurn() {
        this.turnCount++;
        return this.turnCount;
    }
}

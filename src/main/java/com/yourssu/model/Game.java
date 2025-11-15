package com.yourssu.model;

import com.yourssu.strategy.WinnerChecker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Game {
    private final UUID id;
    private final Board board;
    private final Turn turn;
    private Piece winner = null;
    private GameStatus status = GameStatus.IN_PROGRESS;
    private Coordinate playedCoordinate;
    private List<Coordinate> winningLine;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public Game(Board board, Turn turn){
        this.id = UUID.randomUUID();
        this.board = board;
        this.turn = turn;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }

    public Piece getWinner() {
        return winner;
    }

    public Coordinate getPlayedCoordinate() {
        return playedCoordinate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Coordinate> getWinningLine() {
        return winningLine;
    }

    private void win() {
        winner = turn.getCurrnetPiece();
    }

    public void play(Coordinate coordinate) {
        board.placePiece(coordinate.row(), coordinate.column(), turn.getCurrnetPiece());
        List<Coordinate> winningLine = WinnerChecker.checkWinner(board, coordinate);

        if (winningLine != null) {
            win();
            return;
        }

        turn.next();
    }
}

package com.yourssu.model;

import com.yourssu.GameRepository;
import org.springframework.stereotype.Service;


@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(int size) {
        return gameRepository.createGame(size);
    }

    public Game getGame(String gameId) {
        return gameRepository.getGame(gameId);
    }

    public Game placeStone(String gameId, Coordinate coordinate, String player) {
        Game game = gameRepository.getGame(gameId);
        validatePlayer(game, player);
;
        game.play(coordinate);
        return game;
    }

    private void validatePlayer(Game game, String player) {
        String currentPlayer = game.getTurn().getCurrnetPiece().name();

        if (!currentPlayer.equals(player)) {
            throw new IllegalArgumentException("잘못된 플레이어입니다.");
        }
    }
}

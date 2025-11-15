package com.yourssu.web.repository;

import com.yourssu.domain.Game;
import com.yourssu.domain.Status;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;


@Repository
public class GameRepository {
    private final Map<UUID, Game> games;

    public GameRepository(Map<UUID, Game> games) {
        this.games = games;
    }

    public Game createGame(UUID id) {
        this.games.put(id, new Game());
        return this.games.get(id);
    }

    public Game getGame(UUID id) {
        return this.games.get(id);
    }
}

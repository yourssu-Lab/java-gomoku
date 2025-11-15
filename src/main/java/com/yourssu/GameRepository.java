package com.yourssu;

import com.yourssu.model.Board;
import com.yourssu.model.BoardImpl;
import com.yourssu.model.Game;
import com.yourssu.model.Turn;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class GameRepository {
    Map<UUID, Game> gameMap;

    public Game createGame(int size){
        Board board = new BoardImpl(size);
        Turn turn = new Turn();
        Game game = new Game(board, turn);
        this.gameMap.put(game.getId(), game);
        return game;
    }

    public Game getGame(String id) {
        return gameMap.get(UUID.fromString(id));
    }
}

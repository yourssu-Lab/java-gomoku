package com.yourssu.web.repository;

import com.yourssu.domain.Move;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.UUID;

@Repository
public class MoveRepository {
    private final Map<UUID, List<Move>> moves;

    public MoveRepository(Map<UUID, List<Move>> moves) {
        this.moves = moves;
    }

    public List<Move> createMove(UUID id) {
        this.moves.put(id, new ArrayList<Move>());
        return this.moves.get(id);
    }

    public List<Move> getMoveEntires(UUID id) {
        return this.moves.get(id);
    }

    public Move move(UUID id, Move move) {
        this.moves.get(id).add(move);
        return move;
    }
}

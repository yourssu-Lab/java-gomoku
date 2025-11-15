package com.yourssu.web.repository;

import com.yourssu.domain.Board;
import com.yourssu.domain.BoardImpl;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;

@Repository
public class BoardRepository {
    private final Map<UUID, Board> boards;

    public BoardRepository(Map<UUID, Board> boards) {
        this.boards = boards;
    }

    public Board createBoard(UUID id, int size) {
        this.boards.put(id, new BoardImpl(size));
        return this.boards.get(id);
    }

    public Board getBoard(UUID id) {
        return this.boards.get(id);
    }
}

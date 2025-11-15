package com.yourssu.web.service;

import com.yourssu.domain.*;
import com.yourssu.web.controller.dto.CreateGameRequest;
import com.yourssu.web.controller.dto.CreateGameResponse;
import com.yourssu.web.controller.dto.GetGameStatusResponse;
import com.yourssu.web.controller.dto.PlaceStoneRequest;
import com.yourssu.web.repository.BoardRepository;
import com.yourssu.web.repository.GameRepository;
import com.yourssu.web.repository.MoveRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GomokuService {
    private final Rule rule;
    private final GameRepository gameRepository;
    private final MoveRepository moveRepository;
    private final BoardRepository boardRepository;

    public GomokuService(
            Rule rule,
            GameRepository gameRepository,
            BoardRepository boardRepository,
            MoveRepository moveRepository) {
        this.rule = rule;
        this.gameRepository = gameRepository;
        this.boardRepository = boardRepository;
        this.moveRepository = moveRepository;
    }

    // POST /games
    public CreateGameResponse createGameAndMap(CreateGameRequest request) {
        UUID uuid = UUID.randomUUID();

        Board board = this.boardRepository.createBoard(uuid, request.size());
        this.moveRepository.createMove(uuid);
        Game game = this.gameRepository.createGame(uuid);

        return new CreateGameResponse(
                uuid,
                board.getSize(),
                game.getStatus(),
                game.getCurrentTurn(),
                game.getCreatedAt()
        );
    }


    public GetGameStatusResponse getGameStatus(UUID gameId) {
        Game game = this.gameRepository.getGame(gameId);
        Board board = this.boardRepository.getBoard(gameId);
        List<Move> move = this.moveRepository.getMoveEntires(gameId);

        return new GetGameStatusResponse(
                gameId,
                board.getSize(),
                game.getStatus(),
                game.getCurrentTurn(),
                game.getWinner(),
                board.,
                game.getTurnCount(),
                move.getLast(),
                game.getCreatedAt(),
                move.getLast().placedAt()
        );
    }

    private String getWinner() {

    }

    // POST /games/{gameId}/stones
    public Game placeStone(UUID gameId, Piece player, PlaceStoneRequest request) {


        return null; // save한 game의 결과를 반환
    }

}

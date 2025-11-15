package com.yourssu.controller;

import com.yourssu.model.GameService;
import com.yourssu.view.dto.*;
import com.yourssu.model.Game;
import com.yourssu.view.implement.OutputMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/games")
    public ResponseEntity<CommonResponse<CreateGameResponse>> createGame(
            @RequestBody CreateGameRequest request
    ) {

        Game game = gameService.createGame(request.size());
        CreateGameResponse responseDto = OutputMapper.createGame(game);

        CommonResponse<CreateGameResponse> result = CommonResponse.of(responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<CommonResponse<GetGameResponse>> getGame(
            @RequestHeader("X-Player") String player,
            @PathVariable String gameId
    ) {
        Game game = gameService.getGame(gameId);
        GetGameResponse responseDto = OutputMapper.getGame(game);

        CommonResponse<GetGameResponse> result = CommonResponse.of(responseDto);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/games/{gameId}/stones")
    public ResponseEntity<CommonResponse<PlaceStoneResponse>> place(
            @RequestHeader("X-Player") String player,
            @PathVariable String gameId,
            @RequestBody CoordinateDTO coordinate
    ) {
        Game game = gameService.placeStone(gameId, CoordinateDTO.toDomain(coordinate), player);
        PlaceStoneResponse responseDto = OutputMapper.toPlaceStoneResponse(game);
        CommonResponse<PlaceStoneResponse> result = CommonResponse.of(responseDto);

        return ResponseEntity.ok(result);
    }
}

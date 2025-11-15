package com.yourssu.web.controller;

import com.yourssu.annotation.PieceHeader;
import com.yourssu.annotation.VersionedRestController;
import com.yourssu.domain.Piece;
import com.yourssu.web.controller.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@VersionedRestController(version = 1, basePath = "games")
public class GomokuController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Response.SuccessBody<CreateGameResponse>> createGame(
            @PieceHeader Piece piece,
            @RequestBody CreateGameRequest createGameRequest
    ) {
        return Response.created(new CreateGameResponse());
    }


    @GetMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response.SuccessBody<GetGameStatusResponse>> getGameStatus(
            @PieceHeader Piece piece,
            @PathVariable UUID gameId
    ) {
        return Response.ok(new GetGameStatusResponse());
    }

    @PostMapping("/{gameId}/stones")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response.SuccessBody<PlaceStoneResponse>> placeStone(
            @PieceHeader Piece piece,
            @PathVariable UUID gameId,
            @RequestBody PlaceStoneRequest placeStoneRequest
    ) {
        return Response.ok(new PlaceStoneResponse());
    }
}

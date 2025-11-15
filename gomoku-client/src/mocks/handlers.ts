import { http, HttpResponse } from 'msw';
import type { GameState, CreateGameRequest, PlaceStoneRequest, Player } from '../types/game';
import { checkWinner, isPositionOccupied, isValidPosition } from './gameLogic';

const games = new Map<string, GameState>();
let gameIdCounter = 1;

function generateGameId(): string {
  return `game-${gameIdCounter++}`;
}

function createApiResponse<T>(result: T) {
  return {
    timestamp: new Date().toISOString(),
    result,
  };
}

function createApiError(error: string) {
  return {
    timestamp: new Date().toISOString(),
    error,
  };
}

export const handlers = [
  http.post('/api/v1/games', async ({ request }) => {
    const body = (await request.json()) as CreateGameRequest;
    const boardSize = body.boardSize || 15;

    if (boardSize < 5 || boardSize > 15) {
      return HttpResponse.json(
        createApiError('Board size must be between 5 and 15'),
        { status: 400 }
      );
    }

    const gameId = generateGameId();
    const gameState: GameState = {
      gameId,
      boardSize,
      currentTurn: 'BLACK',
      status: 'IN_PROGRESS',
      stones: [],
    };

    games.set(gameId, gameState);

    return HttpResponse.json(createApiResponse(gameState), { status: 201 });
  }),

  http.get('/api/v1/games/:gameId', ({ params }) => {
    const { gameId } = params;
    const game = games.get(gameId as string);

    if (!game) {
      return HttpResponse.json(
        createApiError('Game not found'),
        { status: 404 }
      );
    }

    return HttpResponse.json(createApiResponse(game));
  }),

  http.post('/api/v1/games/:gameId/stones', async ({ request, params }) => {
    const { gameId } = params;
    const game = games.get(gameId as string);

    if (!game) {
      return HttpResponse.json(
        createApiError('Game not found'),
        { status: 404 }
      );
    }

    const player = request.headers.get('X-Player') as Player;
    if (!player || (player !== 'BLACK' && player !== 'WHITE')) {
      return HttpResponse.json(
        createApiError('Invalid or missing X-Player header'),
        { status: 400 }
      );
    }

    if (game.status !== 'IN_PROGRESS') {
      return HttpResponse.json(
        createApiError('Game is not in progress'),
        { status: 400 }
      );
    }

    if (game.currentTurn !== player) {
      return HttpResponse.json(
        createApiError(`It's not ${player}'s turn`),
        { status: 400 }
      );
    }

    const body = (await request.json()) as PlaceStoneRequest;
    const { position } = body;

    if (!isValidPosition(position, game.boardSize)) {
      return HttpResponse.json(
        createApiError('Invalid position'),
        { status: 400 }
      );
    }

    if (isPositionOccupied(game.stones, position)) {
      return HttpResponse.json(
        createApiError('Position already occupied'),
        { status: 400 }
      );
    }

    game.stones.push({ position, player });

    const winner = checkWinner(game.stones, game.boardSize);
    if (winner) {
      game.status = 'COMPLETED';
      game.winner = winner;
    } else if (game.stones.length === game.boardSize * game.boardSize) {
      game.status = 'DRAW';
    } else {
      game.currentTurn = player === 'BLACK' ? 'WHITE' : 'BLACK';
    }

    return HttpResponse.json(createApiResponse(game));
  }),
];

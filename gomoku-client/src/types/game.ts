export type Player = 'BLACK' | 'WHITE';

export type GameStatus = 'IN_PROGRESS' | 'COMPLETED' | 'DRAW';

export interface Position {
  column: string;
  row: number;
}

export interface Stone {
  position: Position;
  player: Player;
}

export interface GameState {
  gameId: string;
  boardSize: number;
  currentTurn: Player;
  status: GameStatus;
  winner?: Player;
  stones: Stone[];
}

export interface ApiResponse<T> {
  timestamp: string;
  result: T;
}

export interface ApiError {
  timestamp: string;
  error: string;
}

export interface CreateGameRequest {
  boardSize?: number;
}

export interface PlaceStoneRequest {
  position: Position;
}

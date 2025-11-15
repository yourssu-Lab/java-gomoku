import type {
  ApiResponse,
  CreateGameRequest,
  GameState,
  PlaceStoneRequest,
  Player
} from '../types/game';
import { getBackendUrl, getUseMock } from '../config/settings';

class ApiClient {
  private getBaseUrl(): string {
    const useMock = getUseMock();
    if (useMock) {
      return '/api/v1';
    }
    return getBackendUrl();
  }

  private async request<T>(
    endpoint: string,
    options: RequestInit = {}
  ): Promise<T> {
    const response = await fetch(`${this.getBaseUrl()}${endpoint}`, {
      ...options,
      headers: {
        'Content-Type': 'application/json',
        ...options.headers,
      },
    });

    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.error || `HTTP error! status: ${response.status}`);
    }

    const data: ApiResponse<T> = await response.json();
    return data.result;
  }

  async createGame(request: CreateGameRequest = {}): Promise<GameState> {
    return this.request<GameState>('/games', {
      method: 'POST',
      body: JSON.stringify(request),
    });
  }

  async getGameStatus(gameId: string): Promise<GameState> {
    return this.request<GameState>(`/games/${gameId}`);
  }

  async placeStone(
    gameId: string,
    player: Player,
    request: PlaceStoneRequest
  ): Promise<GameState> {
    return this.request<GameState>(`/games/${gameId}/stones`, {
      method: 'POST',
      headers: {
        'X-Player': player,
      },
      body: JSON.stringify(request),
    });
  }
}

export const apiClient = new ApiClient();

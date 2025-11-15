import { useState } from 'react';
import { Board } from './components/Board';
import { GameInfo } from './components/GameInfo';
import { Settings } from './components/Settings';
import { apiClient } from './services/api';
import type { GameState, Player, Position } from './types/game';

function App() {
  const [gameState, setGameState] = useState<GameState | null>(null);
  const [currentPlayer, setCurrentPlayer] = useState<Player>('BLACK');
  const [boardSize, setBoardSize] = useState(15);
  const [error, setError] = useState<string>('');
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState<string>('');
  const [showSettings, setShowSettings] = useState(false);

  const showMessage = (message: string, type: 'error' | 'success') => {
    if (type === 'error') {
      setError(message);
      setTimeout(() => setError(''), 5000);
    } else {
      setSuccess(message);
      setTimeout(() => setSuccess(''), 3000);
    }
  };

  const createNewGame = async () => {
    try {
      setLoading(true);
      setError('');
      const newGame = await apiClient.createGame({ boardSize });
      setGameState(newGame);
      showMessage('New game created!', 'success');
    } catch (err) {
      showMessage(err instanceof Error ? err.message : 'Failed to create game', 'error');
    } finally {
      setLoading(false);
    }
  };

  const refreshGameState = async () => {
    if (!gameState) return;

    try {
      setLoading(true);
      const updatedGame = await apiClient.getGameStatus(gameState.gameId);
      setGameState(updatedGame);
      showMessage('Game state refreshed', 'success');
    } catch (err) {
      showMessage(err instanceof Error ? err.message : 'Failed to refresh game', 'error');
    } finally {
      setLoading(false);
    }
  };

  const handleCellClick = async (position: Position) => {
    if (!gameState || gameState.status !== 'IN_PROGRESS') return;

    try {
      setLoading(true);
      setError('');
      const updatedGame = await apiClient.placeStone(
        gameState.gameId,
        currentPlayer,
        { position }
      );
      setGameState(updatedGame);
      showMessage(`Stone placed at ${position.column}${position.row}`, 'success');

      if (updatedGame.status === 'IN_PROGRESS') {
        setCurrentPlayer(updatedGame.currentTurn);
      }
    } catch (err) {
      showMessage(err instanceof Error ? err.message : 'Failed to place stone', 'error');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 py-8 px-4">
      <div className="max-w-6xl mx-auto">
        <div className="flex items-center justify-between mb-8">
          <h1 className="text-4xl font-bold text-gray-800">
            Gomoku Game Client
          </h1>
          <button
            onClick={() => setShowSettings(true)}
            className="bg-white text-gray-700 px-4 py-2 rounded-lg shadow hover:bg-gray-50 font-semibold transition-colors flex items-center gap-2"
          >
            <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
            </svg>
            Settings
          </button>
        </div>

        {/* Error/Success Messages */}
        {error && (
          <div className="mb-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded-lg">
            {error}
          </div>
        )}
        {success && (
          <div className="mb-4 p-4 bg-green-100 border border-green-400 text-green-700 rounded-lg">
            {success}
          </div>
        )}

        {/* Game Setup */}
        {!gameState && (
          <div className="bg-white rounded-lg shadow-lg p-6 max-w-md mx-auto">
            <h2 className="text-2xl font-semibold mb-4">Start New Game</h2>
            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Board Size (5-15)
                </label>
                <input
                  type="number"
                  min="5"
                  max="15"
                  value={boardSize}
                  onChange={(e) => setBoardSize(Number(e.target.value))}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              <button
                onClick={createNewGame}
                disabled={loading}
                className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 disabled:bg-gray-400 disabled:cursor-not-allowed font-semibold transition-colors"
              >
                {loading ? 'Creating...' : 'Create Game'}
              </button>
            </div>
          </div>
        )}

        {/* Game Board and Info */}
        {gameState && (
          <div className="grid md:grid-cols-[1fr,300px] gap-8">
            <div className="flex flex-col items-center space-y-4">
              <Board
                size={gameState.boardSize}
                stones={gameState.stones}
                onCellClick={handleCellClick}
                disabled={
                  loading ||
                  gameState.status !== 'IN_PROGRESS' ||
                  gameState.currentTurn !== currentPlayer
                }
              />

              {gameState.currentTurn !== currentPlayer && gameState.status === 'IN_PROGRESS' && (
                <div className="text-sm text-gray-600 bg-yellow-50 border border-yellow-200 px-4 py-2 rounded-md">
                  It's {gameState.currentTurn}'s turn. You are playing as {currentPlayer}.
                </div>
              )}
            </div>

            <div className="space-y-4">
              <GameInfo
                gameState={gameState}
                currentPlayer={currentPlayer}
                onPlayerChange={setCurrentPlayer}
              />

              <div className="space-y-2">
                <button
                  onClick={refreshGameState}
                  disabled={loading}
                  className="w-full bg-green-600 text-white py-2 px-4 rounded-md hover:bg-green-700 disabled:bg-gray-400 font-semibold transition-colors"
                >
                  Refresh Game
                </button>
                <button
                  onClick={() => setGameState(null)}
                  className="w-full bg-gray-600 text-white py-2 px-4 rounded-md hover:bg-gray-700 font-semibold transition-colors"
                >
                  New Game
                </button>
              </div>
            </div>
          </div>
        )}

        {/* Settings Modal */}
        {showSettings && <Settings onClose={() => setShowSettings(false)} />}
      </div>
    </div>
  );
}

export default App;

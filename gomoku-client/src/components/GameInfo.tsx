import type { GameState, Player } from '../types/game';

interface GameInfoProps {
  gameState: GameState | null;
  currentPlayer: Player;
  onPlayerChange: (player: Player) => void;
}

export function GameInfo({ gameState, currentPlayer, onPlayerChange }: GameInfoProps) {
  if (!gameState) {
    return null;
  }

  const getStatusText = () => {
    if (gameState.status === 'COMPLETED' && gameState.winner) {
      return `${gameState.winner} wins!`;
    }
    if (gameState.status === 'DRAW') {
      return 'Game ended in a draw';
    }
    return `Current turn: ${gameState.currentTurn}`;
  };

  const getStatusColor = () => {
    if (gameState.status === 'COMPLETED') {
      return 'text-green-600';
    }
    if (gameState.status === 'DRAW') {
      return 'text-yellow-600';
    }
    return 'text-blue-600';
  };

  return (
    <div className="bg-white rounded-lg shadow-lg p-6 space-y-4">
      <div className="space-y-2">
        <div className="flex items-center justify-between">
          <span className="text-sm text-gray-600">Game ID:</span>
          <span className="font-mono text-sm">{gameState.gameId}</span>
        </div>

        <div className="flex items-center justify-between">
          <span className="text-sm text-gray-600">Board Size:</span>
          <span className="font-semibold">{gameState.boardSize}×{gameState.boardSize}</span>
        </div>

        <div className="flex items-center justify-between">
          <span className="text-sm text-gray-600">Status:</span>
          <span className={`font-semibold ${getStatusColor()}`}>
            {getStatusText()}
          </span>
        </div>

        <div className="flex items-center justify-between">
          <span className="text-sm text-gray-600">Stones Placed:</span>
          <span className="font-semibold">{gameState.stones.length}</span>
        </div>
      </div>

      <div className="border-t pt-4">
        <label className="block text-sm font-medium text-gray-700 mb-2">
          You are playing as:
        </label>
        <div className="flex gap-2">
          <button
            onClick={() => onPlayerChange('BLACK')}
            className={`flex-1 py-2 px-4 rounded-md font-semibold transition-colors ${
              currentPlayer === 'BLACK'
                ? 'bg-black text-white'
                : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
            }`}
          >
            BLACK
          </button>
          <button
            onClick={() => onPlayerChange('WHITE')}
            className={`flex-1 py-2 px-4 rounded-md font-semibold transition-colors ${
              currentPlayer === 'WHITE'
                ? 'bg-white text-black border-2 border-gray-300'
                : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
            }`}
          >
            WHITE
          </button>
        </div>
      </div>
    </div>
  );
}

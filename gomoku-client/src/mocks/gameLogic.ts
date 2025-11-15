import type { GameState, Player, Position } from '../types/game';

export function checkWinner(stones: GameState['stones'], boardSize: number): Player | null {
  if (stones.length === 0) return null;

  const board: (Player | null)[][] = Array(boardSize)
    .fill(null)
    .map(() => Array(boardSize).fill(null));

  stones.forEach(stone => {
    const colIndex = stone.position.column.charCodeAt(0) - 'A'.charCodeAt(0);
    const rowIndex = stone.position.row - 1;
    board[rowIndex][colIndex] = stone.player;
  });

  const directions = [
    [0, 1],
    [1, 0],
    [1, 1],
    [1, -1],
  ];

  for (let row = 0; row < boardSize; row++) {
    for (let col = 0; col < boardSize; col++) {
      const player = board[row][col];
      if (!player) continue;

      for (const [dx, dy] of directions) {
        let count = 1;
        for (let i = 1; i < 5; i++) {
          const newRow = row + dx * i;
          const newCol = col + dy * i;
          if (
            newRow < 0 ||
            newRow >= boardSize ||
            newCol < 0 ||
            newCol >= boardSize ||
            board[newRow][newCol] !== player
          ) {
            break;
          }
          count++;
        }
        if (count >= 5) {
          return player;
        }
      }
    }
  }

  return null;
}

export function isPositionOccupied(
  stones: GameState['stones'],
  position: Position
): boolean {
  return stones.some(
    s => s.position.column === position.column && s.position.row === position.row
  );
}

export function isValidPosition(position: Position, boardSize: number): boolean {
  const colIndex = position.column.charCodeAt(0) - 'A'.charCodeAt(0);
  return (
    colIndex >= 0 &&
    colIndex < boardSize &&
    position.row >= 1 &&
    position.row <= boardSize
  );
}

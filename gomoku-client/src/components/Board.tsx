import type { Position, Stone, Player } from '../types/game';

interface BoardProps {
  size: number;
  stones: Stone[];
  onCellClick: (position: Position) => void;
  disabled: boolean;
}

export function Board({ size, stones, onCellClick, disabled }: BoardProps) {
  const columns = 'ABCDEFGHIJKLMNO'.slice(0, size).split('');
  const rows = Array.from({ length: size }, (_, i) => i + 1);

  const getStoneAt = (column: string, row: number): Player | null => {
    const stone = stones.find(
      s => s.position.column === column && s.position.row === row
    );
    return stone ? stone.player : null;
  };

  return (
    <div className="inline-block bg-amber-700 p-4 rounded-lg shadow-xl">
      <div className="grid gap-0" style={{ gridTemplateColumns: `repeat(${size}, 1fr)` }}>
        {rows.map(row => (
          columns.map(column => {
            const stone = getStoneAt(column, row);
            return (
              <button
                key={`${column}${row}`}
                onClick={() => !disabled && !stone && onCellClick({ column, row })}
                disabled={disabled || !!stone}
                className="w-10 h-10 border border-amber-900 bg-amber-600 hover:bg-amber-500 disabled:cursor-not-allowed relative transition-colors"
                aria-label={`${column}${row}`}
              >
                {stone && (
                  <div
                    className={`absolute inset-1 rounded-full ${
                      stone === 'BLACK'
                        ? 'bg-black shadow-md'
                        : 'bg-white shadow-md border-2 border-gray-400'
                    }`}
                  />
                )}
              </button>
            );
          })
        ))}
      </div>

      {/* Row and column labels */}
      <div className="flex justify-around mt-2 text-xs text-amber-200 font-mono">
        {columns.map(col => (
          <span key={col} className="w-10 text-center">{col}</span>
        ))}
      </div>
    </div>
  );
}

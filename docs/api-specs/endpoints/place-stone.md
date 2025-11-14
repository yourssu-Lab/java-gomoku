# placeStone (POST /api/v1/games/{gameId}/moves)

지정한 게임의 좌표에 돌을 놓습니다.

## Path Parameters

| Name     | Type   | Required | Description |
|----------|--------|----------|-------------|
| `gameId` | string | Yes      | 게임 고유 식별자   |

## Request

### Request Header

| Name       | Type   | Required | Description        |
|------------|--------|----------|--------------------|
| `X-Player` | string | Yes      | `BLACK` or `WHITE` |

### Request Body

| Name         | Type       | Required | Description |
|--------------|------------|----------|-------------|
| `coordinate` | Coordinate | Yes      | 돌을 놓을 좌표    |

### Coordinate Object

| Name     | Type    | Required | Constraint | Description |
|----------|---------|----------|------------|-------------|
| `column` | string  | Yes      | A~O        | 열           |
| `row`    | integer | Yes      | 1~15       | 행           |

### Example Request

```json
{
  "coordinate": {
    "column": "A",
    "row": 1
  }
}
```

## Reply

### Response Body

| Name   | Type | Nullable | Description |
|--------|------|----------|-------------|
| `move` | Move | No       | 방금 둔 수 정보   |
| `game` | Game | No       | 업데이트된 게임 상태 |

### Move Object

| Name         | Type       | Nullable | Description |
|--------------|------------|----------|-------------|
| `player`     | string     | No       | 플레이어        |
| `coordinate` | Coordinate | No       | 좌표          |
| `turnNumber` | integer    | No       | 턴 번호        |
| `placedAt`   | string     | No       | 수를 둔 시각     |

### Game Object

| Name          | Type         | Nullable | Description                |
|---------------|--------------|----------|----------------------------|
| `gameId`      | string       | No       | 게임 고유 식별자 (생성 방식은 구현자가 선택) |
| `size`        | integer      | No       | 보드 크기                      |
| `status`      | string       | No       | 게임 상태                      |
| `currentTurn` | string       | Yes      | 다음 차례 플레이어                 |
| `winner`      | string       | Yes      | 승리자                        |
| `winningLine` | Coordinate[] | Yes      | 승리한 5개 돌의 좌표               |
| `board`       | string[]     | No       | 보드 상태                      |
| `turnCount`   | integer      | No       | 턴 수                        |
| `updatedAt`   | string       | No       | 업데이트 시각                    |

### Example Response (게임 진행 중)

```json
{
  "timestamp": "2025-11-14T15:11:00Z",
  "result": {
    "move": {
      "player": "BLACK",
      "coordinate": {
        "column": "C",
        "row": 3
      },
      "turnNumber": 3,
      "placedAt": "2025-11-14T15:11:00Z"
    },
    "game": {
      "gameId": "game-001",
      "size": 15,
      "status": "IN_PROGRESS",
      "currentTurn": "WHITE",
      "winner": null,
      "winningLine": null,
      "board": [
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "##X############",
        "#O#############",
        "X##############"
      ],
      "turnCount": 3,
      "updatedAt": "2025-11-14T15:11:00Z"
    }
  }
}
```

### Example Response (게임 종료 - 승리)

```json
{
  "timestamp": "2025-11-14T15:15:00Z",
  "result": {
    "move": {
      "player": "BLACK",
      "coordinate": {
        "column": "E",
        "row": 5
      },
      "turnNumber": 9,
      "placedAt": "2025-11-14T15:15:00Z"
    },
    "game": {
      "gameId": "game-001",
      "size": 15,
      "status": "COMPLETED",
      "currentTurn": null,
      "winner": "BLACK",
      "winningLine": [
        {
          "column": "A",
          "row": 1
        },
        {
          "column": "B",
          "row": 2
        },
        {
          "column": "C",
          "row": 3
        },
        {
          "column": "D",
          "row": 4
        },
        {
          "column": "E",
          "row": 5
        }
      ],
      "board": [
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "###############",
        "####X##########",
        "###X###########",
        "##X############",
        "#XOO###########",
        "XOOO###########"
      ],
      "turnCount": 9,
      "updatedAt": "2025-11-14T15:15:00Z"
    }
  }
}
```

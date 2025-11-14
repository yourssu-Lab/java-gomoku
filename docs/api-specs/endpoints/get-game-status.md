# getGameStatus (GET /api/v1/games/{gameId})

지정한 게임의 상태를 조회합니다.

## Path Parameters

| Name     | Type   | Required | Description |
|----------|--------|----------|-------------|
| `gameId` | string | Yes      | 게임 고유 식별자   |

## Request

### Request Header

| Name       | Type   | Required | Description        |
|------------|--------|----------|--------------------|
| `X-Player` | string | Yes      | `BLACK` or `WHITE` |

## Reply

### Response Body

| Name          | Type     | Nullable | Description                       |
|---------------|----------|----------|-----------------------------------|
| `gameId`      | string   | No       | 게임 고유 식별자 (생성 방식은 구현자가 선택)        |
| `size`        | integer  | No       | 보드 크기                             |
| `status`      | string   | No       | 게임 상태                             |
| `currentTurn` | string   | Yes      | 현재 차례 플레이어 (게임 종료 시 null)         |
| `winner`      | string   | Yes      | 승리한 플레이어 (`BLACK`, `WHITE`, null) |
| `board`       | string[] | No       | 보드 상태                             |
| `turnCount`   | integer  | No       | 현재 턴 수                            |
| `lastMove`    | Move     | Yes      | 마지막으로 둔 수                         |
| `createdAt`   | string   | No       | 게임 생성 시각                          |
| `updatedAt`   | string   | No       | 마지막 업데이트 시각                       |

### Move Object

| Name         | Type       | Nullable | Description             |
|--------------|------------|----------|-------------------------|
| `player`     | string     | No       | 플레이어 (`BLACK`, `WHITE`) |
| `coordinate` | Coordinate | No       | 돌을 놓은 좌표                |
| `turnNumber` | integer    | No       | 턴 번호                    |

### Coordinate Object

| Name     | Type    | Nullable | Description |
|----------|---------|----------|-------------|
| `column` | string  | No       | 열 (A~O)     |
| `row`    | integer | No       | 행 (1~15)    |

### Example Response

```json
{
  "timestamp": "2025-11-14 15:10:30",
  "result": {
    "gameId": "game-001",
    "size": 15,
    "status": "IN_PROGRESS",
    "currentTurn": "WHITE",
    "winner": null,
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
      "###############",
      "#O#############",
      "X##############"
    ],
    "turnCount": 2,
    "lastMove": {
      "player": "WHITE",
      "coordinate": {
        "column": "B",
        "row": 2
      },
      "turnNumber": 2
    },
    "createdAt": "2025-11-14T15:09:00Z",
    "updatedAt": "2025-11-14T15:10:30Z"
  }
}
```

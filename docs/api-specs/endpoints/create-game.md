# createGame (POST /api/v1/games)

새로운 오목 게임을 생성합니다.

## Request

### Request Header

| Name       | Type   | Required | Description        |
|------------|--------|----------|--------------------|
| `X-Player` | string | Yes      | `BLACK` or `WHITE` |

### Request Body

| Name   | Type    | Required | Constraint    | Description     |
|--------|---------|----------|---------------|-----------------|
| `size` | integer | No       | 5 ≤ size ≤ 15 | 보드 크기 (기본값: 15) |

### Example Request

```json
{
  "size": 15
}
```

## Reply

### Response Body

| Name          | Type     | Nullable | Description                                |
|---------------|----------|----------|--------------------------------------------|
| `gameId`      | string   | No       | 게임 고유 식별자 (생성 방식은 구현자가 선택)                 |
| `size`        | integer  | No       | 보드 크기                                      |
| `status`      | string   | No       | 게임 상태 (`IN_PROGRESS`, `COMPLETED`, `DRAW`) |
| `currentTurn` | string   | No       | 현재 차례 플레이어 (`BLACK`, `WHITE`)              |
| `board`       | string[] | No       | 보드 상태 (각 행을 문자열로 표현)                       |
| `createdAt`   | string   | No       | 게임 생성 시각 (ISO-8601 UTC)                    |

### Example Response

```json
{
  "timestamp": "2025-11-14 15:09:00",
  "result": {
    "gameId": "game-001",
    "size": 15,
    "status": "IN_PROGRESS",
    "currentTurn": "BLACK",
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
      "###############",
      "###############"
    ],
    "createdAt": "2025-11-14T15:09:00Z"
  }
}
```

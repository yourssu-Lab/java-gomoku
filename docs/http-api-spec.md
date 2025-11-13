# Gomoku REST API (Spring Boot)

본 문서는 README 요구사항에 맞춰 Spring Boot 기반으로 구현할 Gomoku REST API의 HTTP 명세를 정의한다. 모든 응답은 UTF-8 `application/json`을 사용하고, 시간은 ISO-8601 UTC 문자열을 따른다. Base URL은 `/api/v1`로 통일한다.

> **단일 게임 전제**  
운영 중인 게임은 단 하나다. 서버는 항상 동일한 `/api/v1/game` 리소스를 노출하며, 새 게임을 시작할 때 기존 상태를 초기화한다.

## 공통 규칙

- **Board 사이즈**: 5 이상 15 이하 정수. 기본값 15.
- **Player**: `BLACK`, `WHITE` 두 값만 허용.
- **게임 상태**: `IN_PROGRESS`, `COMPLETED`, `DRAWN`, `RESIGNED`, `CANCELLED`.
- **돌 표기**: 서버는 내부적으로 `BLACK` = `X`, `WHITE` = `O`, 빈 칸 = `#` 를 사용하며, 응답에 보드 스냅샷을 문자열 배열 형태로 포함한다.
- **좌표 표기**: `column`(A~O), `row`(1~15) 조합. 클라이언트가 보낸 값은 서버가 대문자로 정규화.
- **Turn 규칙**: 서버가 현재 차례를 판단한다. 클라이언트는 Custom Header 로 본인이 어느 색인지 알리고 서버 상태와 다르면 409 충돌을 반환한다.

### 플레이어 구분용 헤더

- 헤더 이름: `X-Gomoku-Player`
- 허용 값: `BLACK`, `WHITE`
- 적용 범위: 돌을 두거나(`POST /game/moves`), 상태 변경(`PATCH /game`), 관리 삭제(`DELETE /game`) 등 플레이어 행위가 수반되는 모든 요청
- 헤더가 없거나 값이 틀리면 `400 Bad Request`

## 인증 및 권한

기본 시나리오에서는 인증이 없다고 가정한다. 운영 배포 시에는 Spring Security/Token 기반 인증을 붙인 뒤 관리용 엔드포인트(`DELETE /game`, 리셋 API 등)에 Role 기반 인가를 적용한다.

## 오류 응답

- Content-Type: `application/problem+json`
- 형식:
  ```json
  {
    "type": "https://docs.gomoku.io/problems/illegal-move",
    "title": "Illegal move",
    "status": 409,
    "detail": "Coordinate A1 is already occupied.",
    "instance": "/api/v1/game",
    "violations": [
      {"field": "coordinate", "message": "Coordinate already used"}
    ]
  }
  ```

## 모델 개요

| 이름 | 설명 |
| --- | --- |
| `Game` | 단일 게임 메타데이터와 현재 보드 상태 |
| `Move` | 턴 순서, 돌 색, 좌표, 시간 |
| `Coordinate` | `column`, `row` 쌍 |

### Game JSON 필드

```json
{
  "size": 15,
  "ruleSet": "STANDARD",
  "status": "IN_PROGRESS",
  "currentTurn": "BLACK",
  "winner": null,
  "winningLine": [],
  "board": [
    "###############",
    "###############",
    "X##############"
  ],
  "lastMoveNumber": 3,
  "createdAt": "2024-05-07T12:34:00Z",
  "updatedAt": "2024-05-07T12:35:30Z"
}
```

## 엔드포인트 상세

### 1. 게임 시작/리셋 — `POST /api/v1/game`

| 항목 | 내용 |
| --- | --- |
| 목적 | 단일 게임을 초기화하고 새 보드를 만든다 |
| 응답 | `201 Created` + `Game` |
| 검증 | board size 범위, ruleSet = `STANDARD` |
| 비고 | 기존 게임이 진행 중이어도 새 보드를 만들며 이전 수 기록은 폐기된다 |

**Request**
```json
{
  "size": 15,
  "ruleSet": "STANDARD",
  "firstPlayer": "BLACK"
}
```

**Response**
```json
{
  "size": 15,
  "ruleSet": "STANDARD",
  "status": "IN_PROGRESS",
  "currentTurn": "BLACK",
  "board": [
    "###############",
    "... 15 lines ..."
  ],
  "createdAt": "2024-05-07T12:34:00Z"
}
```

### 2. 게임 상태 조회 — `GET /api/v1/game`

| 항목 | 내용 |
| --- | --- |
| 목적 | 단일 게임의 현재 보드/메타데이터 조회 |
| 응답 | `200 OK` + `Game` |
| Query | `includeMoves=true` 옵션 시 최근 N개 수를 배열로 포함 |

### 3. 수 두기 — `POST /api/v1/game/moves`

| 항목 | 내용 |
| --- | --- |
| 목적 | 플레이어가 좌표에 돌을 놓는다 |
| 응답 | `201 Created` + `{ "move": Move, "game": Game }` |
| 오류 | 409 (잘못된 차례, 이미 종료, 좌표 중복), 422 (범위 밖) |
| 헤더 | `X-Gomoku-Player: BLACK` 또는 `WHITE` 필수 |

**Request**
```json
{
  "coordinate": {
    "column": "A",
    "row": 1
  }
}
```

**Response**
```json
{
  "move": {
    "number": 5,
    "player": "BLACK",
    "coordinate": {"column": "E", "row": 5},
    "placedAt": "2024-05-07T12:41:10Z"
  },
  "game": {
    "status": "COMPLETED",
    "winner": "BLACK",
    "winningLine": [
      {"column": "A", "row": 1},
      {"column": "B", "row": 2},
      {"column": "C", "row": 3},
      {"column": "D", "row": 4},
      {"column": "E", "row": 5}
    ]
  }
}
```

### 4. 수 기록 조회 — `GET /api/v1/game/moves`

| 항목 | 내용 |
| --- | --- |
| 목적 | 전체 move history 제공 |
| Query | `cursor`, `limit` (기본 30) |
| 응답 | `200 OK` + 배열 또는 cursor 기반 |

```json
{
  "moves": [
    {"number": 1, "player": "BLACK", "coordinate": {"column": "A", "row": 1}},
    {"number": 2, "player": "WHITE", "coordinate": {"column": "B", "row": 2}}
  ],
  "nextCursor": "eyJwYWdlIjoyfQ=="
}
```

### 5. 상태 변경 — `PATCH /api/v1/game`

| 항목 | 내용 |
| --- | --- |
| 목적 | `RESIGN`, `DECLARE_DRAW` 같은 제한된 액션 |
| 응답 | `200 OK` + 업데이트된 `Game` |
| 검증 | 이미 종료된 게임에 대한 중복 액션 금지(409) |
| 헤더 | `X-Gomoku-Player` 필수 |

**Request**
```json
{
  "action": "RESIGN"
}
```

### 6. 게임 삭제 — `DELETE /api/v1/game`

| 항목 | 내용 |
| --- | --- |
| 목적 | 테스트/관리자 용도. 상태를 비우고 응답 없이 종료 |
| 응답 | `204 No Content` |
| 헤더 | `X-Gomoku-Player: BLACK` 혹은 권한 있는 값. 운영환경에서는 ADMIN 토큰과 함께 사용 |
| 비고 | 애플리케이션이 즉시 새 게임을 시작하지 않으며 `GET /game` 호출 시 404를 반환하도록 할 수 있다 |

## 서버 측 검증 요약

- 좌표 문자열과 정수 범위 검사
- `X-Gomoku-Player` 헤더와 서버가 추적 중인 현재 차례 일치 여부
- 돌 중복, 게임 종료 후 추가 수 방지
- 승리 조건: 가로/세로/대각선 5개 연속 확인 후 `winner` 세팅
- 무승부: 모든 칸이 채워진 경우 `DRAWN`
- 잘못된 입력은 `IllegalArgumentException`을 래핑하여 400/422 문제 상세 응답으로 변환

## 향후 작업

1. 위 명세를 바탕으로 Spring REST Docs 또는 OpenAPI 3 문서를 자동 산출.
2. `GameController`, `MoveService`, `BoardValidator` 등 계층 구조 구현.
3. README에 명시된 콘솔 출력 예시는 REST API 응답과 동기화하도록 통합 테스트 작성.

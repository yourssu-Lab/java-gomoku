# Gomoku REST API Specification

## 개요

두 명의 플레이어가 HTTP 요청을 통해 번갈아 돌을 놓으며 게임을 진행합니다. 
다중 게임을 지원하며, 각 게임은 고유한 ID로 관리합니다.

## 공통 사항

### Base URL

```
/api/v1
```

### Content-Type

- Request: `application/json`
- Response: `application/json; charset=UTF-8`

### 플레이어 구분 헤더

모든 게임 관련 요청에는 플레이어를 구분하기 위한 헤더가 필요합니다.

| Header Name | Required | Allowed Values   | Description     |
|-------------|----------|------------------|-----------------|
| `X-Player`  | Yes      | `BLACK`, `WHITE` | 요청을 보내는 플레이어 식별 |

### 응답 형식

모든 응답은 아래 형식을 따릅니다:

```json
{
  "timestamp": "2025-11-14T15:09:00Z",
  "result": {
    /* 실제 데이터 */
  }
}
```

오류 응답:

```json
{
  "timestamp": "2025-11-14T15:09:00Z",
  "error": "Error message"
}
```

### 좌표 표기

- `column`: A~O (15x15 보드 기준)
- `row`: 1~15
- 예시: `A1`, `B2`, `O15`

### 게임 규칙

- 보드 크기: 5 이상 15 이하 (기본값 15)
- 선공: BLACK (흑돌, X로 표시)
- 후공: WHITE (백돌, O로 표시)
- 승리 조건: 가로, 세로, 대각선으로 5개 연속
- 무승부: 모든 칸이 채워진 경우

---

## API 엔드포인트

### 게임 관리

| Method | Endpoint                       | Description | 상세 문서                                           |
|--------|--------------------------------|-------------|-------------------------------------------------|
| POST   | `/api/v1/games`                | 새 게임 생성     | [createGame](./endpoints/create-game.md)        |
| GET    | `/api/v1/games/{gameId}`       | 게임 상태 조회    | [getGameStatus](./endpoints/get-game-status.md) |
| POST   | `/api/v1/games/{gameId}/stones` | 돌 놓기        | [placeStone](./endpoints/place-stone.md)        |

---

## 게임 플로우

1. **게임 생성**: `POST /api/v1/games` 호출로 새 게임 생성 → 게임 ID 반환
2. **돌 놓기**: BLACK 플레이어가 `POST /api/v1/games/{gameId}/stones` 호출
3. **상태 확인**: 필요시 `GET /api/v1/games/{gameId}`로 현재 상태 조회
4. **돌 놓기**: WHITE 플레이어가 `POST /api/v1/games/{gameId}/stones` 호출
5. **반복**: 승리 조건이 만족되거나 무승부가 될 때까지 2-4 반복
6. **게임 종료**: `status`가 `COMPLETED` 또는 `DRAW`가 되면 종료

## 구현 요구사항

### 기술 스택

- Java 21
- Spring Boot 3.x
- RESTful API 설계 원칙 준수

### 구현 자유도

다음 항목들은 구현자가 자유롭게 선택할 수 있습니다:

- **게임 ID 생성 방식**: UUID, 순차 번호, nanoid 등 고유성을 보장하는 방식
- **HTTP 상태 코드**: 상황에 맞는 적절한 상태 코드 (2xx, 4xx, 5xx)
- **에러 응답 메시지**: 명확하고 유용한 에러 메시지

### 검증 및 예외 처리

- 상황에 맞는 적절한 HTTP 상태 코드 반환

### 코드 품질

- Java 코드 컨벤션 준수 (https://naver.github.io/hackday-conventions-java)
- 메서드 길이: 10줄 이내
- 들여쓰기 깊이: 1단계만 허용
- else, 3항 연산자, switch/case 사용 금지
- 인스턴스 변수: 최대 3개
- 일급 컬렉션 사용
- Getter/Setter 지양
- 주석 대신 명확한 네이밍

### 테스트

- 핵심 비즈니스 로직에 대한 단위 테스트 작성
- API 엔드포인트 통합 테스트 작성

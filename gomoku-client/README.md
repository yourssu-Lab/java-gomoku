# Gomoku Game Client

React + TypeScript + Tailwind CSS 기반 Gomoku 게임 클라이언트입니다.

## 기능

- 새 게임 생성 (보드 크기 5-15 선택 가능)
- 실시간 게임 진행
- 플레이어 선택 (BLACK/WHITE)
- 게임 상태 조회 및 갱신
- 승리/무승부 감지
- 에러 처리 및 사용자 피드백

## 시작하기

### 설치

```bash
npm install
```

### 개발 서버 실행

```bash
npm run dev
```

기본적으로 `http://localhost:5173`에서 실행됩니다.

**개발 모드에서는 MSW(Mock Service Worker)를 사용하여 Mock API가 자동으로 활성화됩니다.**
백엔드 서버 없이도 바로 게임을 테스트할 수 있습니다!

### 빌드

```bash
npm run build
```

## API 서버 설정

### Mock API (개발 모드)

개발 환경에서는 MSW를 사용한 Mock API가 자동으로 활성화됩니다:
- 게임 생성, 돌 놓기, 승리 감지 등 모든 기능이 클라이언트에서 동작
- 브라우저 콘솔에서 `[MSW]` 로그를 확인할 수 있습니다
- 실제 백엔드 없이 완전한 게임 플레이 가능

Mock API 구현:
- `src/mocks/handlers.ts` - API 엔드포인트 핸들러
- `src/mocks/gameLogic.ts` - 게임 로직 (승리 조건, 유효성 검증)
- `src/mocks/browser.ts` - MSW 워커 설정

### 실제 API 서버 연결

프로덕션 빌드에서는 `http://localhost:8080/api/v1`에 연결합니다.

다른 주소를 사용하려면 `src/services/api.ts` 파일의 `BASE_URL`을 수정하세요:

```typescript
const BASE_URL = import.meta.env.DEV ? '/api/v1' : 'http://your-api-server:port/api/v1';
```

## 사용 방법

1. **새 게임 시작**
   - 보드 크기 선택 (5-15)
   - "Create Game" 버튼 클릭

2. **게임 플레이**
   - 자신의 색(BLACK 또는 WHITE) 선택
   - 자신의 차례일 때 보드의 빈 칸 클릭하여 돌 놓기
   - 상대방의 차례는 경고 메시지로 표시됨

3. **게임 상태 확인**
   - "Refresh Game" 버튼으로 현재 게임 상태 갱신
   - 게임 정보 패널에서 현재 상태, 차례, 놓인 돌 개수 확인

4. **새 게임 시작**
   - "New Game" 버튼으로 현재 게임 종료 및 새 게임 설정 화면으로 이동

## 프로젝트 구조

```
src/
├── components/
│   ├── Board.tsx          # 게임 보드 컴포넌트
│   └── GameInfo.tsx       # 게임 정보 및 컨트롤 패널
├── mocks/
│   ├── browser.ts         # MSW 워커 설정
│   ├── handlers.ts        # API Mock 핸들러
│   └── gameLogic.ts       # 게임 로직 (승리 조건 등)
├── services/
│   └── api.ts             # API 클라이언트
├── types/
│   └── game.ts            # TypeScript 타입 정의
├── App.tsx                # 메인 앱 컴포넌트
├── main.tsx               # 앱 진입점 (MSW 초기화)
└── index.css              # Tailwind CSS 설정
```

## API 엔드포인트

- `POST /api/v1/games` - 새 게임 생성
- `GET /api/v1/games/{gameId}` - 게임 상태 조회
- `POST /api/v1/games/{gameId}/stones` - 돌 놓기 (헤더: `X-Player: BLACK|WHITE`)

## 기술 스택

- **React 18** - UI 라이브러리
- **TypeScript** - 타입 안전성
- **Vite** - 빌드 도구
- **Tailwind CSS** - 스타일링
- **MSW (Mock Service Worker)** - API 모킹
- **Fetch API** - HTTP 클라이언트

## Mock API 기능

Mock API는 실제 백엔드 API 스펙을 완전히 구현합니다:

### 구현된 기능
- ✅ 게임 생성 (보드 크기 유효성 검증)
- ✅ 게임 상태 조회
- ✅ 돌 놓기 (차례 검증, 위치 유효성 검증)
- ✅ 승리 조건 체크 (가로, 세로, 대각선 5개 연속)
- ✅ 무승부 감지 (보드가 가득 참)
- ✅ 에러 처리 및 적절한 HTTP 상태 코드 반환

### 게임 로직
- 15x15 또는 사용자 지정 크기 보드 지원 (5-15)
- 흑돌(BLACK)이 선공, 백돌(WHITE)이 후공
- 5개 연속 배치 시 승리
- 모든 칸이 채워지면 무승부

## 개발 팁

### Mock API 사용 시
- 개발 서버 실행 후 바로 게임 플레이 가능
- 브라우저 콘솔에서 `[MSW]` 로그로 API 호출 확인
- Network 탭에서 가로채진 요청 확인 가능
- 게임 로직은 `src/mocks/gameLogic.ts`에서 수정 가능

### 실제 API 서버 연결 시
- 프로덕션 빌드 시 Mock API가 자동으로 비활성화됨
- CORS 이슈가 있다면 백엔드 서버에서 CORS 설정 필요
- API 서버는 `http://localhost:8080`에서 실행되어야 함

const STORAGE_KEYS = {
  USE_MOCK: 'gomoku_use_mock',
  BACKEND_URL: 'gomoku_backend_url',
} as const;

export const DEFAULT_BACKEND_URL = 'http://localhost:8080/api/v1';

export function getUseMock(): boolean {
  const stored = localStorage.getItem(STORAGE_KEYS.USE_MOCK);
  return stored === null ? true : stored === 'true';
}

export function setUseMock(value: boolean): void {
  localStorage.setItem(STORAGE_KEYS.USE_MOCK, String(value));
  window.location.reload();
}

export function getBackendUrl(): string {
  return localStorage.getItem(STORAGE_KEYS.BACKEND_URL) || DEFAULT_BACKEND_URL;
}

export function setBackendUrl(url: string): void {
  localStorage.setItem(STORAGE_KEYS.BACKEND_URL, url);
}

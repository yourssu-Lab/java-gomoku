import { useState } from 'react';
import {
  getUseMock,
  setUseMock,
  getBackendUrl,
  setBackendUrl,
  DEFAULT_BACKEND_URL,
} from '../config/settings';

interface SettingsProps {
  onClose: () => void;
}

export function Settings({ onClose }: SettingsProps) {
  const [useMock, setUseMockState] = useState(getUseMock());
  const [backendUrl, setBackendUrlState] = useState(getBackendUrl());
  const [showSaved, setShowSaved] = useState(false);

  const handleToggleMock = () => {
    const newValue = !useMock;
    setUseMock(newValue);
    setUseMockState(newValue);
  };

  const handleSaveUrl = () => {
    setBackendUrl(backendUrl);
    setShowSaved(true);
    setTimeout(() => setShowSaved(false), 2000);
  };

  const handleReset = () => {
    setBackendUrlState(DEFAULT_BACKEND_URL);
    setBackendUrl(DEFAULT_BACKEND_URL);
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg shadow-xl p-6 max-w-md w-full mx-4">
        <div className="flex items-center justify-between mb-6">
          <h2 className="text-2xl font-bold text-gray-800">Settings</h2>
          <button
            onClick={onClose}
            className="text-gray-500 hover:text-gray-700 text-2xl leading-none"
          >
            ×
          </button>
        </div>

        <div className="space-y-6">
          {/* MSW Toggle */}
          <div className="border-b pb-4">
            <div className="flex items-center justify-between">
              <div>
                <h3 className="font-semibold text-gray-800">Use Mock API</h3>
                <p className="text-sm text-gray-600 mt-1">
                  Enable MSW (Mock Service Worker) for testing without backend
                </p>
              </div>
              <button
                onClick={handleToggleMock}
                className={`relative inline-flex h-6 w-11 items-center rounded-full transition-colors ${
                  useMock ? 'bg-blue-600' : 'bg-gray-300'
                }`}
              >
                <span
                  className={`inline-block h-4 w-4 transform rounded-full bg-white transition-transform ${
                    useMock ? 'translate-x-6' : 'translate-x-1'
                  }`}
                />
              </button>
            </div>
            {!useMock && (
              <div className="mt-2 p-2 bg-yellow-50 border border-yellow-200 rounded text-xs text-yellow-800">
                Page will reload when toggled
              </div>
            )}
          </div>

          {/* Backend URL */}
          <div>
            <h3 className="font-semibold text-gray-800 mb-2">Backend API URL</h3>
            <p className="text-sm text-gray-600 mb-3">
              {useMock
                ? 'Mock API is active. This URL is ignored.'
                : 'Enter the backend server URL'}
            </p>
            <input
              type="text"
              value={backendUrl}
              onChange={(e) => setBackendUrlState(e.target.value)}
              disabled={useMock}
              placeholder="http://localhost:8080/api/v1"
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100 disabled:cursor-not-allowed text-sm font-mono"
            />
            <div className="flex gap-2 mt-3">
              <button
                onClick={handleSaveUrl}
                disabled={useMock}
                className="flex-1 bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 disabled:bg-gray-400 disabled:cursor-not-allowed text-sm font-semibold transition-colors"
              >
                Save URL
              </button>
              <button
                onClick={handleReset}
                disabled={useMock}
                className="px-4 py-2 border border-gray-300 text-gray-700 rounded-md hover:bg-gray-50 disabled:bg-gray-100 disabled:cursor-not-allowed text-sm font-semibold transition-colors"
              >
                Reset
              </button>
            </div>
            {showSaved && (
              <div className="mt-2 p-2 bg-green-50 border border-green-200 rounded text-xs text-green-800">
                Backend URL saved successfully!
              </div>
            )}
          </div>

          {/* Info */}
          <div className="bg-blue-50 border border-blue-200 rounded p-3">
            <p className="text-xs text-blue-800">
              <strong>Current Mode:</strong> {useMock ? 'Mock API' : 'Real Backend'}
              <br />
              <strong>API Endpoint:</strong>{' '}
              <code className="bg-blue-100 px-1 rounded">
                {useMock ? '/api/v1' : backendUrl}
              </code>
            </p>
          </div>
        </div>

        <div className="mt-6 pt-4 border-t">
          <button
            onClick={onClose}
            className="w-full bg-gray-600 text-white py-2 px-4 rounded-md hover:bg-gray-700 font-semibold transition-colors"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
}

# /infra

Technical infrastructure — handles low-level logic not directly related to the UI.

## Subfolders:
- `api/`: Axios setup, interceptors, etc.
- `query/`: React Query configuration (`queryClient`, `defaultOptions`)
- `workers/`: Web Worker scripts (for heavy processing off the main thread)
- `i18n/`: Internationalization (if applicable)

> This is the "infrastructure" layer and should not contain any domain-specific logic.

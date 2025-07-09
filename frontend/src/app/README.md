# /app

The main entry point folder of the application.

## Sub-structure:
- `App.tsx`: Root component, wraps all providers and the router.
- `Router.tsx`: Defines the main routes of the application.
- `providers/`: Contains context providers or global wrappers such as:
  - `ReactQueryProvider.tsx`
  - `SuspenseBoundary.tsx`

> Should only contain app-wide logic (no domain-specific logic).

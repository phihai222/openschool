# /shared

Contains all reusable elements shared across features in the app.

## Subfolders:
- `components/`: Reusable UI components.
  - `ui/`: Button, Modal, Spinner, Skeleton, etc.
  - `layout/`: Header, Footer, Sidebar, etc.
  - `feedback/`: Toast, ErrorFallback, etc.
  - `loading/`: AppLoadingOverlay, RouteLoading, ComponentSkeleton, etc.
- `hooks/`: Shared utility hooks (debounce, network status, focus detection, etc.)
- `store/`: Global Zustand stores (auth, loading state, user data, etc.)
- `utils/`: Small utility functions (formatDate, retryWithJitter, etc.)
- `constants/`: Shared constants (API endpoints, theme values, etc.)
- `types/`: Global TypeScript types (e.g., `global.d.ts`, etc.)
- `HOC/`: Higher-order components (e.g., `withSuspense`)

> Absolutely no domain-specific logic should be written here!

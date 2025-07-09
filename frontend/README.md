# ⚡ ReactJS + Vite + Zustand + React Query

> A scalable, production-ready React project setup using Vite, Zustand, React Query, SCSS Modules, and Storybook — fully structured by feature/domain.

---

## 📝Node Version
>v22.16.0

## 🛠️ Features

### 🧱 Architecture
- **Vite**: Ultra-fast dev/build environment
- **Zustand**: Global state management with `persist`
- **React Query**: Powerful data-fetching with caching, background refetching
- **Axios**: Custom instance with interceptor, refresh token queue, retry with jitter
- **Storybook**: Isolated component development and documentation
- **SCSS Modules**: Theming, mixins, breakpoints
- **PWA-ready**: Installable, offline support (planned)
- **i18n-ready**: Support multiple languages
---

## 📦 Tech Stack
| Purpose       | Library/Tool       |
| ------------- | ------------------ |
| Frontend      | React, Vite        |
| State Mgmt    | Zustand            |
| Data Fetching | React Query        |
| HTTP Client   | Axios              |
| Routing       | React Router       |
| Styling       | SCSS Modules       |
| Build Tool    | Vite               |
| Component Dev | Storybook          |
| Utilities     | Custom hooks/utils |
| PWA           | (Planned)          |

## 🗂 Folder Structure
```yaml
src/
│
├── app/ # App entry, layout, providers, routing
│ ├── App.tsx
│ ├── Router.tsx
│ └── providers/
│ ├── ReactQueryProvider.tsx
│ └── SuspenseBoundary.tsx
│
├── features/ # Domain-specific features
│ ├── auth/
│ │ ├── components/
│ │ ├── hooks/
│ │ ├── services/
│ │ ├── pages/
│ │ └── types.ts
│ ├── dashboard/
│ └── settings/
│
├── shared/ # Shared UI, hooks, store, utils across features
│ ├── components/
│ │ ├── ui/ # Reusable UI components (Button, Modal, Spinner, ...)
│ │ ├── layout/ # Header, Footer, Sidebar
│ │ ├── feedback/ # ErrorFallback, Toast
│ │ └── loading/ # Loading components per layer (App, Route, Component)
│ ├── hooks/
│ ├── store/
│ ├── utils/
│ ├── constants/
│ ├── types/
│ └── HOC/
│
├── infra/ # Infrastructure layer
│ ├── api/ # Axios instance, interceptors
│ ├── query/ # React Query client config
│ ├── workers/ # Web Workers
│ └── i18n/ # Internationalization (optional)
│
├── styles/ # Global SCSS setup
│ ├── _variables.scss
│ ├── _mixins.scss
│ ├── _breakpoints.scss
│ ├── theme.scss
│ └── main.scss
│
├── assets/ # Images, icons, fonts
├── index.tsx # Entry point
└── ...
```

---
## 🚀 Getting Started
### 1. Add file .env

To see the debug log set `VITE_DEV=true`
```dotenv
VITE_API_URL=xxxxxxxxxxxxxxxx
VITE_DEV=false
```

```bash
# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview

# Format code with Prettier
npm run format

# Lint code
npm run lint

# Run unit tests (optional)
npm run test

# Run Storybook
npm run storybook
```
---
## 📘 Storybook

Storybook is set up for developing and documenting UI components in isolation.

### Run Storybook:

```bash
# Run storybook
npm run storybook

# Build storybook
npm run build-storybook
```
---

## 🌐 Internationalization (i18n)
Language files are stored in: public/locales/{lang}/{namespace}.json

Supports dynamic import, preload, and routing via /en/*, /vi/*, etc.

```tsx
const { t } = useTranslation('common');
t('welcome', { name: 'Quan' });
```
---
## 🌓 Theme (Dark/Light)
Automatically persisted in localStorage

Class (`dark` or `light`) is injected into `<html>`

Use with Zustand:

```tsx
import { useThemeStore } from '@/shared/store/useThemeStore';

const { theme, toggleTheme } = useThemeStore();

```

## 🧠 Custom Hooks & Features
useDebounce, useNetworkStatus, useFocusTab

Reusable loading states:

AppLoading, RouteLoading, ComponentSkeleton

retryWithJitter for network retry strategy

withSuspense HOC for lazy components

---

## 📈 Optimization Goals
🧩 Modular, scalable codebase

⚡ Fast loading with lazy & suspense

🔁 Offline-first (PWA), long-press and haptic-ready on mobile

🌐 Language-ready with i18n support

🧪 Extensible testing with Vitest/Jest


# 🌐 React Vite Multilingual Setup (i18next)

This project is pre-configured with **i18next** and **react-i18next** to support:
- ✅ Multiple languages (e.g., English, Vietnamese)
- ✅ Split JSON files by feature/module (namespaces)
- ✅ Runtime interpolation (e.g., `{{name}}`)

---

## 🏗️ Project Structure
```
public/
└── locales/
├── en/
│ ├── common.json
│ ├── auth.json
│ └── dashboard.json
└── vi/
├── common.json
├── auth.json
└── dashboard.json
```
- `common.json`: shared texts
- `auth.json`: authentication-related texts
- `dashboard.json`: dashboard-related texts
---

## ⚙️ Initialization (`src/app/providers/ReactI18n.tsx`)

```tsx
import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import HttpBackend from 'i18next-http-backend';
import LanguageDetector from 'i18next-browser-languagedetector';

i18n
  .use(HttpBackend)
  .use(LanguageDetector)
  .use(initReactI18next)
  .init({
    fallbackLng: 'en',
    supportedLngs: ['en', 'vi'],
    ns: ['common', 'auth', 'dashboard'], // Declare namespaces here
    defaultNS: 'common',
    interpolation: {
      escapeValue: false, // Required for React
    },
    backend: {
      loadPath: '/locales/{{lng}}/{{ns}}.json',
    },
    detection: {
      order: ['localStorage', 'navigator'],
      caches: ['localStorage'],
    },
    debug: import.meta.env.MODE === 'development',
  });

export default i18n;
```
---
## 💬 Usage
### 🔹 Basic usage
```jsx
import { useTranslation } from 'react-i18next';

const Welcome = () => {
  const { t } = useTranslation('common'); // match common.json

  return <p>{t('welcome')}</p>;
};
```
`public/locales/en/common.json`

```json
{
  "welcome": "Welcome!"
}
```
---
### 🔹 With interpolation (variable support)
```jsx
<p>{t('greeting', { name: 'Quan' })}</p>
```
`
public/locales/en/common.json
`
```json
{
  "greeting": "Hello, {{name}}!"
}
```

---
### 🔹 With namespace switch
```jsx
const Login = () => {
  const { t } = useTranslation('auth'); // match auth.json

  return <button>{t('login')}</button>;
};
```

---
## 🔄 Change Language
```tsx
import i18n from '@/app/providers/ReactI18n';

const changeLanguage = (lng: 'en' | 'vi') => {
  i18n.changeLanguage(lng);
};
```
---
## 📦 Add New Language
Create folder `public/locales/fr/`

Add corresponding JSON files: `common.json`, `auth.json, etc`.

Update `supportedLngs` in the i18n config

---
## 🧪 Tips for Development
✅ Use `t('key', { var })` instead of string concatenation

✅ Keep JSON keys consistent across languages

✅ Avoid deeply nested keys

✅ Use defaultNS when common strings are most used

---
## 📁 Related Files
| File                              | Description                         |
| --------------------------------- | ----------------------------------- |
| `src/app/providers/ReactI18n.tsx` | Main config for i18n                |
| `public/locales/`                 | Language resource files             |
| `useTranslation()`                | React hook for localized strings    |
| `i18n.changeLanguage()`           | Manually switch language at runtime |

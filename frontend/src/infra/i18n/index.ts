import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import I18NextHttpBackend from "i18next-http-backend";
import I18nextBrowserLanguageDetector from "i18next-browser-languagedetector";

i18n
    .use(I18NextHttpBackend)
    .use(I18nextBrowserLanguageDetector)
    .use(initReactI18next)
    .init({
        fallbackLng: 'vi',
        supportedLngs: ['en', 'vi'],
        defaultNS: 'common',
        ns: ['common'], // default namespace
        backend: {
            loadPath: '/locales/{{lng}}/{{ns}}.json', // Load file JSON
        },
        interpolation: {
            escapeValue: false, // For React (no need to escape HTML)
        },
        detection: {
            // Detect from path (route) then localStorage
            order: ['path', 'localStorage', 'navigator'],
            lookupFromPathIndex: 1, // e.g., /vi/home
            caches: ['localStorage'], // Save to localStorage
        },
        react: {
            useSuspense: true,
        },
        debug: import.meta.env.VITE_DEV_MODE === 'development',
    });

export default i18n;
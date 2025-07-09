import React, {ReactNode, useEffect} from 'react';
import { I18nextProvider } from 'react-i18next';
import i18n  from "@infra/i18n/index.js"

const preloadLanguages = ['vi', 'en'];

const I18nProvider = ({children}: {children: ReactNode}) => {


    useEffect(() => {
        // auto preload another language
        preloadLanguages.forEach((lng) => {
            if (lng !== i18n.language) {
                i18n.loadLanguages(lng);
            }
        });

        // Detect language from URL path if window.location is available
        if (typeof window !== 'undefined') {
            const pathLang = window.location.pathname.split('/')[1];
            if (preloadLanguages.includes(pathLang) && i18n.language !== pathLang) {
                i18n.changeLanguage(pathLang);
            }
        }
    }, []);

    return (
        <I18nextProvider i18n={i18n}>
            {children}
        </I18nextProvider>
    );
};

export default I18nProvider;
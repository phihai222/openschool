import { useTranslation } from 'react-i18next';

export const useCurrentLanguage = () => {
    const { i18n } = useTranslation();

    const currentLang = i18n.language;

    const changeLanguage = (lng: 'vi' | 'en') => {
        if (lng !== i18n.language) {
            i18n.changeLanguage(lng);
            localStorage.setItem('i18nextLng', lng); // Save manually if needed
        }
    };

    return {
        currentLang,
        changeLanguage,
        isVi: currentLang === 'vi',
        isEn: currentLang === 'en',
    };
};
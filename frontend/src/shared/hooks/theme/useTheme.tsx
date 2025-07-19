import {useThemeStore} from "@shared/store/useThemeStore/index.js";

export const useTheme = () => {
    const theme = useThemeStore((s) => s.theme);
    const toggleTheme = useThemeStore((s) => s.toggleTheme);
    const setTheme = useThemeStore((s) => s.setTheme);

    return {
        theme,
        toggleTheme,
        setTheme,
        isDark: theme === 'dark',
        isLight: theme === 'light',
    };
};

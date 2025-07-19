import { create } from 'zustand';
import { persist } from 'zustand/middleware';

type Theme = 'light' | 'dark';

interface ThemeState {
    theme: Theme;
    toggleTheme: () => void;
    setTheme: (theme: Theme) => void;
}

const applyThemeToDOM = (theme: Theme) => {
    document.documentElement.classList.remove('light', 'dark');
    document.documentElement.classList.add(theme);
};


export const useThemeStore = create<ThemeState>()(
    persist(
        (set) => ({
            theme: 'light',

            toggleTheme: () =>
                set((state) => {
                    const newTheme = state.theme === 'light' ? 'dark' : 'light';
                    applyThemeToDOM(newTheme);
                    return { theme: newTheme };
                }),

            setTheme: (theme) => {
                applyThemeToDOM(theme);
                set({ theme });
            },
        }),
        {
            name: 'app-theme', // localStorage key
            onRehydrateStorage: () => (state) => {
                if (state) applyThemeToDOM(state.theme); // gắn class sau khi rehydrate
            },
        }
    )
);
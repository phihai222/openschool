import {create} from 'zustand';
import {persist} from "zustand/middleware";

export const useAuthStore = create()(
  persist(
    (set) => ({
      accessToken: null,
      refreshToken: null,
      isAuthenticated: false,

      setLogin: (accessToken, refreshToken) => {
        set({accessToken, refreshToken, isAuthenticated: true});
      },

      logout: () => {
        set({
          accessToken: null,
          refreshToken: null,
          isAuthenticated: false,
        })
      },

      setAccessToken: (token) => set({accessToken:token }),
    }),
    {
      name: "auth-storage",
    }
  )
)
import {create} from "zustand";

interface LoadingState {
  appLoading: boolean;
  setAppLoading: (loading: boolean) => void;
}

export const useLoadingApp = create<LoadingState>((set) => ({
  appLoading: false,
  setAppLoading: (appLoading) => set({appLoading}),
}))
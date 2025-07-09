import instance from "@infra/http/axiosInstance.js";
import {useMutation} from "@tanstack/react-query";
import {useAuthStore} from "@/shared/store/useAuthStore/index.js";

export const useRefreshToken = () => {
  return useMutation({
    mutation: async () => {
      const res = await instance.post("/auth/refresh", {})
      return res.data.accessToken;
    },
    onSuccess: (token) => {
      useAuthStore.getState().login(token);
    }
  })
}

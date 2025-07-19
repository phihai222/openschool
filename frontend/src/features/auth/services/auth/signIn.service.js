import axiosInstance from "@infra/http/axiosInstance.js";
import {handleApiError} from "@infra/http/errorHandle.js";

export const signIn = async ({user_name, password}) => {
  try {
    return await axiosInstance.post(`/auth/login`, {
      user_name,
      password
    })
  } catch (error) {
    handleApiError(error, {resource: `User ID ${user_name}`});
  }
}
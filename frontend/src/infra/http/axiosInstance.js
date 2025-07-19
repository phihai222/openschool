import axios from "axios";
import {setupAxiosInterceptors} from "@infra/http/inteceptors.js";

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: 10000,
  "Content-Type": "application/json",
});

setupAxiosInterceptors(axiosInstance)
export default axiosInstance;

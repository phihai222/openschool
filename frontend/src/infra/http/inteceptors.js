import {useAuthStore} from "@/shared/store/useAuthStore/index.js";

let isRefreshing = false;
let refreshSubscribers = [];

const subscribeTokenRefresh = (cb) => {
  refreshSubscribers.push(cb);
};

const onRefreshed = (token) => {
  refreshSubscribers.forEach((cb) => cb(token));
  refreshSubscribers = [];
};

export const setupAxiosInterceptors = (axiosInstance) => {
  // Add a request interceptor
axiosInstance.interceptors.request.use(
  function (config) {
    // debug log in local
    if (import.meta.env.VITE_DEV_MODE === "development") {

      console.log('%c[API REQUEST]', 'color: yellow', {
        url: config.url,
        method: config.method,
        params: config.params,
        data: config.data,
      });
    }


    // Do something before request is sent
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  },
);

// Add a response interceptor
  axiosInstance.interceptors.response.use(
    function (response) {
      // Any status code that lie within the range of 2xx cause this function to trigger
      // Do something with response data

      // debug log in local
      if (import.meta.env.VITE_DEV_MODE === "development") {
        console.log('%c[API RESPONSE]', 'color: pink', {
          url: response.config.url,
          data: response.data,
        });
      }

      return response.data;
    },
    async function (error) {
      // Any status codes that falls outside the range of 2xx cause this function to trigger
      // Do something with response error
      console.error("error", error);
      const originalRequest = error.config;
      const {refreshToken, setAccessToken, logout} = useAuthStore.getState();

      if (
        error.response?.status === 401 &&
        !originalRequest._retry &&
        refreshToken
      ) {
        originalRequest._retry = true;

        if (isRefreshing) {
          return new Promise((resolve) => {
            subscribeTokenRefresh((newToken) => {
              originalRequest.headers.Authorization = `Bearer ${newToken}`;
              resolve(axiosInstance(originalRequest));
            });
          });
        }

        isRefreshing = true;

        // call api get refresh token
        try {
          const response = await axios.post("https://api.example.com/auth/refresh", {
            refreshToken,
          });

          // get new token then set it to store zustand
          const newAccessToken = response.data.accessToken;
          setAccessToken(newAccessToken);

          // call request has an error 401
          onRefreshed(newAccessToken);

          // add new token to header
          originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
          return axiosInstance(originalRequest);
        } catch (err) {
          logout();
          return Promise.reject(err);
        } finally {
          isRefreshing = false;
        }
      }

      return Promise.reject(error);
    },
  );

}




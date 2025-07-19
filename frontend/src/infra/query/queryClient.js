import { QueryClient } from "@tanstack/react-query";


export const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retryDelay: (attemptIndex) =>
        Math.pow(2, attemptIndex) * 3000 + Math.random() * 1000,
      retry: (failureCount, err) => {
        console.log(err);
        if (err.response.status < 200 || err.response.status >= 300) {
          return false; // do not retry, trigger error
        }
        // otherwise, restore default
        const defaultRetry = 3;
        return Number.isSafeInteger(defaultRetry)
          ? failureCount < (defaultRetry ?? 0)
          : false;
      },
      staleTime: 4000 + Math.random() * 1000,
    },
  },
});

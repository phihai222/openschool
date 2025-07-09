import {ReactNode} from "react";
import {QueryClientProvider} from "@tanstack/react-query";
import {queryClient} from "@infra/query/queryClient.js";

export const ReactQueryProvider = ({children}: {children: ReactNode}) => {
    return (
        <QueryClientProvider client={queryClient}>
            {children}
        </QueryClientProvider>
    );
};


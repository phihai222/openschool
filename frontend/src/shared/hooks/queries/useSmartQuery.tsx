import {queryClient} from "../../../infra/query/queryClient";
import {useEffect, useRef} from "react";
import {useQuery} from "@tanstack/react-query";
import useIsOnline from "../useIsOnline";
import useIsTabVisible from "../useIsTabVisible";


export const useSmartQuery = ({serviceFn, enabled = true, dataKey, delayTime = 3000 }) => {
    const debounceTimeRef = useRef(null);
    const isOnline = useIsOnline();
    const isTabVisible = useIsTabVisible();

// call api the first time navigate to screen
    const {
        data,
        status,
        error,
    } = useQuery({
        queryKey: dataKey,
        queryFn: serviceFn,
        enabled: enabled, // chi goi o man hinh dashboard
        refetchOnWindowFocus: false,
        refetchOnReconnect: false,
    })

    const cachedData = queryClient.getQueryData(dataKey);

    useEffect(() => {
        const handleDebouncedRefetch = () => {
            /*
            * Đảm bảo api chỉ gọi khi thoa mãn các điều kiện sau:
            * - đã có kết nối internet
            * - người dùng đang sử dụng tab browser > timeDelay (chỉ delay khi đã có cache data)
            * - query đã được gọi xong
            * */

            if (isOnline && isTabVisible && cachedData) {
                if (debounceTimeRef.current) clearTimeout(debounceTimeRef.current);

                debounceTimeRef.current = setTimeout(() => {
                    queryClient.invalidateQueries({
                        queryKey: dataKey
                    }).then(() => {
                    })
                }, delayTime)
            }
        }

        window.addEventListener('focus', handleDebouncedRefetch);
        window.addEventListener('online', handleDebouncedRefetch);
        document.addEventListener('visibilitychange', handleDebouncedRefetch);

        return () => {
            window.removeEventListener('focus', handleDebouncedRefetch);
            window.removeEventListener('online', handleDebouncedRefetch);
            document.removeEventListener('visibilitychange', handleDebouncedRefetch);
            if (debounceTimeRef.current) clearTimeout(debounceTimeRef.current);
        }
    }, [data, isOnline, isTabVisible, delayTime, cachedData]);

    return {data, status, error};
}
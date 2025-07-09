import {useQueryClient, useQuery, useQueries} from "@tanstack/react-query";
import {useEffect, useState} from "react";

const fetchData = async (id: string) => {
    const res = await fetch(`/api/data/${id}`);
    return res.json();
};

const useBatchedQueries = (ids: string[], batchSize: number = 3) => {
    const queryClient = useQueryClient();
    const [loadedIds, setLoadedIds] = useState<string[]>([]);
    const [batchIndex, setBatchIndex] = useState(0);

    const currentBatch = ids.slice(batchIndex * batchSize, (batchIndex + 1) * batchSize);

    const queries = useQueries({
        queries: currentBatch.map((id) => ({
            queryKey: ['data', id],
            queryFn: () => fetchData(id),
            enabled: true,
            staleTime: Infinity,
        })),
    });

    // Khi batch hiện tại xong, gọi batch tiếp theo
    useEffect(() => {
        if (queries.every(q => q.isSuccess)) {
            setLoadedIds((prev) => [...prev, ...currentBatch]);
            if ((batchIndex + 1) * batchSize < ids.length) {
                setBatchIndex((prev) => prev + 1);
            }
        }
    }, [queries.map(q => q.status).join()]);

    return loadedIds.map((id) => queryClient.getQueryData(['data', id]));
};

import {useMutation, useQueryClient} from '@tanstack/react-query';
import axiosInstance from "../../../infra/http/axiosInstance";

const updateItem = async ({id, data}: { id: string; data: any }) => {
    const res = await axiosInstance.put(`/items/${id}`, data);
    return res.data;
};

export const useUpdateItem = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: updateItem,
        onSuccess: (_data, variables) => {
            queryClient.invalidateQueries({queryKey: ['item', variables.id]});
            queryClient.invalidateQueries({queryKey: ['items']});
        },
    });
};

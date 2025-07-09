import { useMutation, useQueryClient } from '@tanstack/react-query';
import axiosInstance from "../../../infra/http/axiosInstance";

const createItem = async (data: any) => {
    const res = await axiosInstance.post('/items', data);
    return res.data;
};

export const useCreateItem = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: createItem,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['items']}).then();  // refetch list
        },
    });
};

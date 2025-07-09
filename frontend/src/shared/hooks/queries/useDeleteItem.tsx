import { useMutation, useQueryClient } from '@tanstack/react-query';
import axiosInstance from "../../../infra/http/axiosInstance";

const deleteItem = async (id: string) => {
    const res = await axiosInstance.delete(`/items/${id}`);
    return res.data;
};

export const useDeleteItem = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: deleteItem,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['items'] });
        },
    });
};

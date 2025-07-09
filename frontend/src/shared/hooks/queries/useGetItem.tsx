import { useQuery } from '@tanstack/react-query';
import axiosInstance from "../../../infra/http/axiosInstance";

const fetchItem = async (id: string) => {
    const { data } = await axiosInstance.get(`/items/${id}`);
    return data;
};

export const useGetItem = (id: string, enabled = true) => {
    return useQuery({
        queryKey: ['item', id],
        queryFn: () => fetchItem(id),
        enabled: !!id && enabled, // chỉ chạy khi có id
    });
};

import { useQuery } from '@tanstack/react-query';
import axiosInstance from "../../../infra/http/axiosInstance";

const fetchList = async () => {
    const { data } = await axiosInstance.get('/items');
    return data;
};

export const useGetList = () => {
    return useQuery({
        queryKey: ['items'],
        queryFn: fetchList,
        staleTime: 1000 * 60, // 1 phút
    });
};

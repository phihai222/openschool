import {useInfiniteQuery} from "@tanstack/react-query";

const fetchPage = async ({pageParam = 0}) => {
    const res = await fetch(`/api/data?page=${pageParam}&limit=3`);
    return res.json();
};

const usePagingQuery = ({queryKey, options}) => {


    const {data, fetchNextPage, hasNextPage, isFetchingNextPage} = useInfiniteQuery({
        queryKey: queryKey,
        queryFn: ({pageParam}) => fetchPage(pageParam),
        initialPageParam: 1,
        ...options,
        getNextPageParam: (lastPage, allPages, lastPageParam, allPageParams) =>
            lastPage.nextCursor,
        getPreviousPageParam: (firstPage, allPages, firstPageParam, allPageParams) =>
            firstPage.prevCursor,
    })


    return {data, fetchNextPage, hasNextPage, isFetchingNextPage};

}

export default usePagingQuery;
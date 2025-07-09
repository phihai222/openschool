
export async function fakeDelay<T>(promise: Promise<T>, ms: number): Promise<T> {
    return await new Promise((resolve: (arg0: Promise<T>) => any) => {
        setTimeout(() => resolve(promise), ms);
    }); // unwrap Promise<Promise<T>> => Promise<T>
}
import {
    ComponentType,
    LazyExoticComponent,
    Suspense,
    ReactNode, lazy, ComponentProps,
} from 'react';
import ErrorBoundaryComponent from "../components/feedback/ErrorBoundaryComponent";

// Type for options
type WithLazyOptions = {
    fallback?: ReactNode;
    retryCount?: number; // số lần thử lại khi import thất bại
};

// function lazy component with retry
function lazyWithRetry<T extends ComponentType<any>>(
    importFn: () => Promise<{ default: T }>, // take a function import component
    retryCount = 3
): LazyExoticComponent<T> {
    return lazy(() => {

        // number retry
        let retries = retryCount;

        const attempt = (): Promise<{ default: T }> =>
            importFn().catch((err) => {
                if (retries <= 0) throw err; // Check if all retry attempts are exhausted, then throw an error
                retries--;                   // Decrease the retry count on each attempt
                return new Promise((resolve) =>
                    setTimeout(() => resolve(attempt()), 500) // Retry every 500ms between attempts
                );
            });

        return attempt();
    });
}

// function withLazy
export function withLazy<T extends ComponentType<any>>(
    importFn: () => Promise<{ default: T }>,
    options?: WithLazyOptions
) {
    // Call lazyRetry with the imported component and the number of retry attempts
    const LazyComponent = lazyWithRetry(importFn, options?.retryCount ?? 3);

    // Wrap with Suspense to show fallback while loading
    const WrapperComponent = (props: ComponentProps<T>) => {
        return (
            // If no fallback is provided, it will use the default loading indicator
            <ErrorBoundaryComponent>
                <Suspense fallback={options?.fallback ?? <div>Loading...</div>}>
                    <LazyComponent {...props} />
                </Suspense>
            </ErrorBoundaryComponent>

        );
    };

    // Attach preload to the returned component
    (WrapperComponent as any).preload = importFn;

    return WrapperComponent as ComponentType<ComponentProps<T>> & {
        preload: () => Promise<void>;
    };
}

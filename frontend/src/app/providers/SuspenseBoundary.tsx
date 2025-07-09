import {Suspense, ReactNode} from 'react';
import AppLoadingOverlay from "@shared/components/loading/AppLoadingOverlay.jsx";


export const SuspenseBoundary = ({children}: { children: ReactNode }) => {
    return (
        <Suspense fallback={<AppLoadingOverlay/>}>
            {children}
        </Suspense>
    );
};

export default SuspenseBoundary;
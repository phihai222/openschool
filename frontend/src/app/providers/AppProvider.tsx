import {ReactNode} from "react";
import {ReactQueryProvider} from "./ReactQueryProvider.js";
import SuspenseBoundary from "./SuspenseBoundary.js";
import I18nProvider from "@app/providers/I18nProvider.js";


export const AppProvider = ({children}: { children: ReactNode }) => {
    return (
        <ReactQueryProvider>
            <I18nProvider>
                <SuspenseBoundary>
                    {children}
                </SuspenseBoundary>
            </I18nProvider>
        </ReactQueryProvider>
    );
};


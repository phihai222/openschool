import React from 'react';
import ErrorFallback from "@/shared/components/feedback/ErrorBoundaryComponent/ErrorFallback/index.jsx";
import {ErrorBoundary} from "react-error-boundary";

const ErrorBoundaryComponent = ({children}) => {

  const handleReload = () => {
    location.reload();
  }

  return (<ErrorBoundary
      FallbackComponent={ErrorFallback}
      onReset={handleReload}
    >
      {children}
    </ErrorBoundary>);
};

export default ErrorBoundaryComponent;
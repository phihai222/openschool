import React from 'react';

const ErrorFallback = ({error, resetErrorBoundary}) => {
  return (
    <div role="alert" style={{ padding: 20, color: 'red' }}>
      <p>Something went wrong:</p>
      <pre>{error.message}</pre>
      <button onClick={resetErrorBoundary}>Try again</button>
    </div>
  );
};

export default ErrorFallback;
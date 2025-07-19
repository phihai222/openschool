import { useRouteError } from 'react-router';

export default function ErrorPage() {
  const error = useRouteError();

  return (
    <div style={{ padding: '2rem', textAlign: 'center', color: 'red' }}>
      <h1>🚨 Oops! Something went wrong.</h1>
      <p>{error?.statusText || error?.message}</p>
      <button onClick={() => window.location.href = '/'} style={{ marginTop: '1rem' }}>
        Go back to home page
      </button>
    </div>
  );
}

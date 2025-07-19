## HOC Usage Guide

### Importing a Component with HOC
- Import `withLazy` and wrap your component import using it:
```jsx
// Declare the component with lazy loading
const LazyCard = withLazy(() => import('./Card'), {
  fallback: <div>Loading card...</div>, // loading fallback component
  retryCount: 2 // number of retry attempts
});
```
---

### Using the Lazy-Loaded Component
- Useful when you want to preload content in anticipation of user navigation:
```jsx
// Trigger preload on mouse hover
<button onMouseEnter={() => LazyCard.preload()}>
  Go to Card
</button>

// Use it in JSX
<LazyCard someProp="abc" />
```
---


### Testing the Result
Check Suspense Fallback:
- Import fakeDelay and wrap it around the component to simulate loading time.
- Add a delay duration.

Check Lazy Loading:
- Create two screens: A and B.
- The lazy component should be used in screen B.
- Open browser DevTools, go to the Network tab, filter by JS, and enable "Disable cache".
- From screen A, navigate to screen B. At this point:
  - The lazy component should be downloaded.
  - A fallback loading UI should appear, and after 2 seconds the component should display.

Expected behavior:
- When visiting screen A, the lazy component is not loaded.
- It only gets loaded when navigating to screen B.

Check Error Handling:
- Import a broken component (e.g., brokenPage) using lazy loading.
- The error should display in place of the component without crashing the entire app.

```jsx
const LazyCard = withLazy(() => fakeDelay(
  (
    import('./Card'), 
    {
      fallback: <div>Loaddng...</div>, // loading fallback component
      retryCount: 2  // number of retry attempts 
    }
  ),
  2000 // delay of 2000ms
))
```

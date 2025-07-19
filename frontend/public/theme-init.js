(() => {
  try {
    const saved = localStorage.getItem('app-theme');
    if (saved) {
      const parsed = JSON.parse(saved);
      const theme = parsed?.state?.theme;
      if (theme) document.documentElement.classList.add(theme);
    }
  } catch (_) {}
})();

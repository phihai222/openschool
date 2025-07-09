import  {useEffect, useState} from 'react';

const UsePwaDetection = () => {
const [status, setStatus] = useState({
  isPWA: false,
  isIOS: false,
});

  useEffect(() => {
    const isStandalone = window.matchMedia(`(display-mode: standalone)`).matches ||
      (window.navigator).standalone === true;

    const isIOS = /iphone|ipad|ipod/i.test(window.navigator.userAgent);

    setStatus({
      isPWA: isStandalone,
      isIOS: isStandalone && isIOS,
    });
  }, []);

  return {status};
};

export default UsePwaDetection;
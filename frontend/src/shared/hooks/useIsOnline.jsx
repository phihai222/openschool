import React, {useEffect, useState} from 'react';

const UseIsOnline = () => {
  const [isOnline, setIsOnline] = useState(navigator ? navigator.onLine : true);

  useEffect(() => {
    const handleIsOnline = () => {
      setIsOnline(true);
    }

    const handleIsOffline = () => {
      setIsOnline(false);
    }

    window.addEventListener('online', handleIsOnline);
    window.addEventListener('offline', handleIsOffline);

    return () => {
      window.removeEventListener('online', handleIsOnline);
      window.removeEventListener('offline', handleIsOffline);
    }

  }, []);

  return isOnline
};

export default UseIsOnline;
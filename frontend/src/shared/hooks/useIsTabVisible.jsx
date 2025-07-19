import {useEffect, useState} from 'react';

const UseIsTabVisible = () => {
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    const handleIsVisible = () => {
      setIsVisible(true);
    }

    const handleIsVisibilityChange = () => {
      setIsVisible(false);
    }

    window.addEventListener('focus', handleIsVisible);
    window.addEventListener('visibilityChange', handleIsVisibilityChange);

    return () => {
      window.removeEventListener('focus', handleIsVisible);
      window.removeEventListener('visibilityChange', handleIsVisibilityChange);
    }

  }, []);

  return isVisible
};

export default UseIsTabVisible;
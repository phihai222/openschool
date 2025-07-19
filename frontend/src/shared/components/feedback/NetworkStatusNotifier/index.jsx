import {useEffect} from 'react';
import useIsOnline from "@shared/hooks/useIsOnline.jsx";
import {toast} from "react-toastify";

const Index = () => {
  const isOnline = useIsOnline();

  useEffect(() => {
    if (!isOnline) {
      toast("You are offline")
    } else {
      // toast("You are reconnected")
    }
  }, [isOnline]);

  return null
};

export default Index;
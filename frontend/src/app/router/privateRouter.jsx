import React from 'react';
import {useAuthStore} from "@/shared/store/useAuthStore/index.js";
import {Navigate} from "react-router";

export const PrivateRouter = ({children}) => {
  const isAuthenticated = useAuthStore((state) => state.isAuthenticated);

  return isAuthenticated ? children : <Navigate to="/login" replace={true} />;
};


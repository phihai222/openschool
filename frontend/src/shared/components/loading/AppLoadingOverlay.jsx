import React from 'react';
import {useLoadingApp} from "@shared/store/useLoadingApp/index.js";
import AppLoading from "@shared/components/ui/loading/AppLoading/index.js";

const AppLoadingOverlay = () => {
  const appLoading = useLoadingApp((state) => state.appLoading);

  if (!appLoading) return null;

  return (
    <div>
      {/*  component loading */}
      <AppLoading/>
    </div>
  );
};

export default AppLoadingOverlay;
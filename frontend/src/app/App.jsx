import React from "react";
import {RouterProvider} from "react-router";
import {AppProvider} from "@app/providers/AppProvider.js";
import router from "@/app/router/appRouter.jsx";
import "../style/global.scss"

function App() {
  return (
    <AppProvider>
      <RouterProvider router={router}/>
    </AppProvider>
  );
}

export default App;

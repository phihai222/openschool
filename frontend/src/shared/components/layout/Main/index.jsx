import React from 'react';
import {Outlet} from "react-router";
import NetworkStatusNotifier from "@shared/components/feedback/NetworkStatusNotifier/index.jsx";
import NavigateBottom from "@shared/components/layout/NavigateBottom/index.jsx";
import styles from './LayoutMain.module.scss'

const LayoutApp = () => {
  return (
    <div className={styles.mainContainer}>
      <NetworkStatusNotifier/>
      <Outlet/>
      {/*<NavigateBottom/>*/}
    </div>
  );
};

export default LayoutApp;
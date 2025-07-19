import React, {useMemo} from 'react';
import styles from './NavigateBottom.module.scss'
import {NavLink, useLocation} from "react-router";
import {LIST_PATH} from "@shared/constants/listPath.js";
import usePWADetection from "@shared/hooks/pwa/usePWADetection.jsx";


const NavigateBottom = () => {
  const {pathname} = useLocation();
  const { status} = usePWADetection();

  const containerClass = [
    styles.container,
    status.isPWA && status.isIOS ? styles.pwaIosPadding : '',
  ]
    .filter(Boolean)
    .join(' ');

  return (
    <div className={containerClass}>
      {
        LIST_PATH.map((item, index) => (
          <NavLink to={item.path}  key={index} className={`${styles.navigateItem} ${item.path === pathname ? styles.active : styles.inActive}`} >
            <div className={`${styles.icon}`}>Icon</div>
            <span className={styles.navLinkText}> {item.name}</span>
          </NavLink>
         ))
      }
    </div>
  );
};

export default NavigateBottom;
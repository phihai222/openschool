import React from 'react';
import styles from './header.module.scss';

const Header = ({buttonFn, ScreenTitle, buttonBack, navigateFn}) => {

  const dynamicAlign = buttonBack ? styles.titleCenter : styles.titleLeft
  const titleClassName = `${styles.screenTitleContainer} ${dynamicAlign}`

  return (
    <div className={`${styles.headerContainer} ${styles.borderBottom}`}>
      {
        buttonBack && (
          <div className={styles.btnFn}>
            <button className={styles.btnLeft} onClick={navigateFn}>Back</button>
          </div>
        )
      }
      <div className={titleClassName}>
        {ScreenTitle ? ScreenTitle : "Screen Title"}
      </div>
      <div className={`${styles.btnFn} ${styles.btnRight}`}>
        {buttonFn ? buttonFn : "Function"}
      </div>
    </div>
  );
};

export default Header;
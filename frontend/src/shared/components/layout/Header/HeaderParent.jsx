import React from 'react';
import styles from './header.module.scss';

const HeaderParent = ({buttonFn, ScreenTitle}) => {
  const titleClassName = `${styles.screenTitleContainer} ${styles.titleLeft}`

  return (
    <div className={styles.headerContainer}>
      <div className={titleClassName}>
        {ScreenTitle ? ScreenTitle : "Screen Title"}
      </div>
      <div className={`${styles.btnFn} ${styles.btnRight}`}>
        {buttonFn ? buttonFn : null}
      </div>
    </div>
  );
};

export default HeaderParent;
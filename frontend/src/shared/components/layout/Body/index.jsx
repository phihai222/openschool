import React from 'react';
import styles from './body.module.scss'

const Body = ({children}) => {
  return (
    <div className={styles.container}>
      {children}
    </div>
  );
};

export default Body;
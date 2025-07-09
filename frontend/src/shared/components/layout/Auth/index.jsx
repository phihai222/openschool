import React, {Fragment} from 'react';
import NetworkStatusNotifier from "@shared/components/feedback/NetworkStatusNotifier/index.jsx";
import styles from './LayoutAuth.module.scss'

const LayoutAuth = ({children}) => {
  return (
    <Fragment>
      <div className={styles.authContainer}>
        <NetworkStatusNotifier/>
        {children}
      </div>
    </Fragment>
  );
};

export default LayoutAuth;
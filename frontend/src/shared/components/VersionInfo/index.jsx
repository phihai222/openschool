import React from "react";
import styles from "./Version.module.scss";

const VersionInfo = () => {
  return (
    <div className={styles.versionContainer}>
      <p>Version: {__APP_VERSION__}</p>
      <p>Commit: {__APP_COMMIT__}</p>
      <p>Built at: {new Date(__BUILD_TIME__).toLocaleString()}</p>
    </div>
  );
};

export default VersionInfo;

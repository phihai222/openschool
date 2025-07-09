import React, {useRef} from 'react';
import VersionInfo from "@shared/components/VersionInfo/index.jsx";
import Button from "@shared/components/ui/Button/index.tsx";
import {ToastContainer} from 'react-toastify';
import {useTranslation} from 'react-i18next';
import {useCurrentLanguage} from "@shared/hooks/i18n/useCurrentLanguage.tsx";
import useSignIn from "@features/auth/hooks/auth/useSignIn.jsx";
import {useTheme} from "@shared/hooks/theme/useTheme.tsx";
import styles from './Login.module.scss';

const Login = () => {
  const user_nameRef = useRef(null);
  const passwordRef = useRef(null);
  const {mutateLogin, pendingLogin, errorMessage} = useSignIn()
  const {currentLang, changeLanguage} = useCurrentLanguage();
  const {theme, toggleTheme} = useTheme();
  const {t} = useTranslation('auth');

  const onSubmit = (e) => {
    e.preventDefault();
    const user_name = user_nameRef.current?.value ?? "";
    const password = passwordRef.current?.value ?? "";

    console.log(user_name, password);
    mutateLogin({user_name, password});
  }

  return (
    <div className={styles.container}>
      <div>

        <div className={styles.changeLanguage}>
          <p>Change language</p>
          <div>
            <button
              className={styles.buttonChange}
              onClick={() => changeLanguage('vi')}
              disabled={currentLang === 'vi'}
            >
              🇻🇳 Vietnamese
            </button>
            <button
              className={styles.buttonChange}
              onClick={() => changeLanguage('en')}
              disabled={currentLang === 'en'}
            >
              🇺🇸 English
            </button>
          </div>
        </div>

        <div className={styles.changeTheme}>
          <p>Change theme:</p>
          <button onClick={toggleTheme} className={styles.buttonChange}>
            {theme === 'dark' ? '☀️ Light Mode' : '🌙 Dark Mode'}
          </button>
        </div>


        <div className={styles.logo}>
          <p>{t('welcomeBack', {username: 'Quan'})}</p>
        </div>
        <ToastContainer position="top-center"/>
        {
          errorMessage && <div>
            <p className={styles.errorMsg}>{errorMessage}</p>
          </div>
        }
        <form onSubmit={onSubmit} className={styles.formWrapper}>
          <div className={styles.formGroup}>
            <label htmlFor="username" id="username-label" className={styles.labelInput}>
              Username:
            </label>
            <input
              className={styles.inputStyle}
              type="text"
              id="username"
              placeholder="Username"
              ref={user_nameRef}
              autoComplete="current-username"
            />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="password" id="password-label" className={styles.labelInput}>
              Password:
            </label>
            <input
              className={styles.inputStyle}
              type="password"
              id="password"
              placeholder="Password"
              ref={passwordRef}
              autoComplete="current-password"
            />
          </div>
          <Button
            label={pendingLogin ? "Logging in..." : "Sign in"}
            variant="primary"
            disabled={pendingLogin}
            type="submit"
          />
        </form>
      </div>
      <VersionInfo/>
    </div>
  );
};

export default Login;
import { useTranslation } from 'react-i18next';

export const useAppTranslation = (ns?: string) => {
  return useTranslation(ns || 'common');
};
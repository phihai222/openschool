// For more info, see https://github.com/storybookjs/eslint-plugin-storybook#configuration-flat-config-format
import storybook from "eslint-plugin-storybook";

import js from "@eslint/js";
import globals from "globals";
import reactHooks from "eslint-plugin-react-hooks";
import reactRefresh from "eslint-plugin-react-refresh";
import eslintPluginSimpleImportSort from "eslint-plugin-simple-import-sort";

export default [{ignores: ["dist"]}, {
  files: ["**/*.{js,jsx}"],
  globals: {
    __APP_VERSION__: "readonly",
    __APP_COMMIT__: "readonly",
    __BUILD_TIME__: "readonly",
  },
  languageOptions: {
    ecmaVersion: 2020,
    globals: globals.browser,
    parserOptions: {
      ecmaVersion: "latest",
      ecmaFeatures: {jsx: true},
      sourceType: "module",
    },
  },
  plugins: {
    "react-hooks": reactHooks,
    "react-refresh": reactRefresh,
    "simple-import-sort": eslintPluginSimpleImportSort,
  },
  rules: {
    "simple-import-sort/imports": "warn",
    "simple-import-sort/exports": "warn",
    "react-hooks/rules-of-hooks": "error",
    "react-hooks/exhaustive-deps": "warn",
    ...js.configs.recommended.rules,
    ...reactHooks.configs.recommended.rules,
    "no-unused-vars": ["error", {varsIgnorePattern: "^[A-Z_]"}],
    "react-refresh/only-export-components": [
      "warn",
      {
        allowConstantExport: true,
        "additionalHooks": "(useRecoilCallback|useRecoilTransaction_UNSTABLE)"
      },
    ],
  },
}, ...storybook.configs["flat/recommended"]];

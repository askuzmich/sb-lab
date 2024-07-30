module.exports = {
  env: {
    browser: true,
    es2021: true,
    jest: true
  },
  extends: ["plugin:react/recommended", "airbnb", "plugin:i18next/recommended"],
  parser: "@typescript-eslint/parser",
  parserOptions: {
    ecmaFeatures: {
      jsx: true,
    },
    ecmaVersion: "latest",
    sourceType: "module",
  },
  plugins: ["react", "@typescript-eslint", "i18next"],
  rules: {
    "react/jsx-indent": [2, 2],
    quotes: [2, "double"], // "quotes": [2, "single"],
    "no-console": "off",
    "import/no-unresolved": "off",
    "import/extensions": "off",
    "react/jsx-uses-react": "off",
    "react/react-in-jsx-scope": "off",
    "react/jsx-filename-extension": "off",
    "import/prefer-default-export": "off",
    "no-unused-vars": "off",
    "no-undef": "off",
    "no-shadow": "off",
    "comma-dangle": "off",
    "implicit-arrow-linebreak": "off",
    "no-underscore-dangle": "off",
    "object-curly-newline": "off",
    "react/function-component-definition": "off",
    "react/require-default-props": "off",
    "react/jsx-props-no-spreading": "off",
    "import/no-extraneous-dependencies": "off",
    "arrow-body-style": "off",
    "max-len": ["error", { ignoreComments: true, code: 100 }],
    "i18next/no-literal-string": ["error", { markupOnly: true, onlyAttribute: [""] }]
  },
  globals: { __IS_DEV__: true },
};

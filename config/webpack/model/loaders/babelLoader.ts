import { IOptions } from "../../types";

export const babelLoader = ({ isDev }: IOptions) => {
  return {
    test: /\.(js|jsx|tsx)$/,
    use: {
      loader: "babel-loader",
      options: {
        presets: ["@babel/preset-env"],
        plugins: [
          ["i18next-extract", { locales: ["ru", "en"], keyAsDefaultValue: true }],
          isDev && require.resolve("react-refresh/babel")
        ].filter(Boolean)
      }
    },
    exclude: /node_modules/,
  };
};

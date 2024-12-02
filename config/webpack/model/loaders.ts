import webpack from "webpack";
import MiniCssExtractPlugin from "mini-css-extract-plugin";
import { BuildOptions } from "../types/config";

export function svgLoader() {
  return {
    test: /\.svg$/,
    use: ["@svgr/webpack"]
  };
}

export function cssLoader(isDev: boolean) {
  return {
    test: /\.s[ac]ss$/i,
    use: [
      isDev ? "style-loader" : MiniCssExtractPlugin.loader,
      {
        loader: "css-loader",
        options: {
          modules: {
            auto: (resPath: string) => Boolean(resPath.includes(".module.")),
            // localIdentName: isDev ? "[path][name]__[local]" : "[hash:base64:8]",
            localIdentName: isDev
              ? "[path][name]__[local]--[hash:base64:5]"
              : "[hash:base64:8]",
          },
        },
      },
      "sass-loader",
    ],
  };
}

export function loaders({ isDev }: BuildOptions): webpack.RuleSetRule[] {
  const svgLdr = svgLoader();

  const fileLoader = {
    test: /\.(png|jpe?g|gif|woff2|woff)$/i,
    use: [{ loader: "file-loader" }],
  };

  const cssLdr = cssLoader(isDev);

  const typescriptLoader = {
    test: /\.tsx?$/,
    use: "ts-loader",
    exclude: /node_modules/,
  };

  return [typescriptLoader, cssLdr, svgLdr, fileLoader];
}

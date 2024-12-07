/**
 *
 *    WebPack Config Builder
 *
 */
import webpack from "webpack";
import { IOptions } from "../types";
import { plugins } from "./plugins";
import { loaders } from "./loaders";
import { resolvers } from "./resolvers";
import { devServer } from "./devServer";

export const build = (options: IOptions): webpack.Configuration => {
  const { paths, mode, isDev } = options;

  return {
    mode,

    entry: paths.entry,

    output: {
      filename: "[name].[contenthash].js",
      path: paths.build,
      clean: true,
      publicPath: "/"
    },

    plugins: plugins(options),

    module: { rules: loaders(options) },

    resolve: resolvers(options),

    devtool: isDev ? "inline-source-map" : undefined,

    devServer: isDev ? devServer(options) : undefined,
  };
};

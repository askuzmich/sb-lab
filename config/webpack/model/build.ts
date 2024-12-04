import webpack from "webpack";
import { BuildOptions } from "../types/config";
import { plugins } from "./plugins";
import { loaders } from "./loaders";
import { resolvers } from "./resolvers";
import { devServer } from "./devServer";

export function build(
  options: BuildOptions
): webpack.Configuration {
  const { paths, mode, isDev } = options;

  return {
    mode,

    entry: paths.entry,

    output: {
      filename: "[name].[contenthash].js",
      path: paths.build,
      clean: true,
    },

    plugins: plugins(options),

    module: {
      rules: loaders(options),
    },

    resolve: resolvers(options),

    devtool: isDev ? "inline-source-map" : undefined,

    devServer: isDev ? devServer(options) : undefined,
  };
}

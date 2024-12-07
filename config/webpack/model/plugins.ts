import HTMLWebpackPlugin from "html-webpack-plugin";
import webpack from "webpack";
import MiniCssExtractPlugin from "mini-css-extract-plugin";
import { BundleAnalyzerPlugin } from "webpack-bundle-analyzer";
import { IOptions } from "../types";

export function plugins({
  paths,
  isDev,
  restBaseUrl,
  projectType
}: IOptions): webpack.WebpackPluginInstance[] {
  const plugins = [
    new HTMLWebpackPlugin({
      template: paths.html,
    }),

    new webpack.ProgressPlugin(),

    new MiniCssExtractPlugin({
      filename: "css/[name].[contenthash:8].css",
      chunkFilename: "css/[name].[contenthash:8].css",
    }),

    new webpack.DefinePlugin({
      __IS_DEV__: JSON.stringify(isDev),
      __REST_API__BASE_URL__: JSON.stringify(restBaseUrl),
      __PROJECT_TYPE__: JSON.stringify(projectType),
    })
  ];

  if (isDev) {
    // plugins.push(new ReactRefreshWebpackPlugin());

    // plugins.push(new webpack.HotModuleReplacementPlugin());

    plugins.push(new BundleAnalyzerPlugin({
      openAnalyzer: false
    }));
  }

  return plugins;
}
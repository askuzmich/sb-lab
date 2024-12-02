import type { Configuration as DevServerConfiguration } from "webpack-dev-server";
import { BuildOptions } from "../types/config";

export function devServer(options: BuildOptions): DevServerConfiguration {
  return {
    port: options.port,
    open: true,
    historyApiFallback: true,
  };
}

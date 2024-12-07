import type { Configuration as DevServerConfiguration } from "webpack-dev-server";
import { IOptions } from "../types";

export function devServer(options: IOptions): DevServerConfiguration {
  return {
    port: options.port,
    open: true,
    historyApiFallback: true,
  };
}

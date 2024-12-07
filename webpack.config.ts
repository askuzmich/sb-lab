import path from "path";
import webpack from "webpack";
import { build, IEnv, IOptions, IPaths } from "./config/webpack";
import { API_ENDPOINT_HOST, API_ENDPOINT_HOST_PORT } from "./src/resources/application";

export default (env: IEnv) => {
  const paths: IPaths = {
    entry: path.resolve(__dirname, "src", "index.tsx"),
    build: path.resolve(__dirname, "build"),
    html: path.resolve(__dirname, "public", "index.html"),
    src: path.resolve(__dirname, "src"),
  };

  const mode = env.mode || "development";
  const isDev = mode === "development";
  const port = env.port || 3000;
  const restBaseUrl = env.restBaseUrl || `${API_ENDPOINT_HOST}:${API_ENDPOINT_HOST_PORT}`;
  const projectType = "frontend";
  const options: IOptions = { paths, mode, isDev, port, restBaseUrl, projectType };

  const config: webpack.Configuration = build(options);

  return config;
};

import path from "path";
import webpack from "webpack";
import { buildWebpackConfig } from "./config/build/buildWebpackConfig";
import { BuildEnv, BuildPaths } from "./config/build/types/config";
import { API_ENDPOINT_HOST, API_ENDPOINT_HOST_PORT } from "./src/resources/application";

export default (env: BuildEnv) => {
  const paths: BuildPaths = {
    entry: path.resolve(__dirname, "src", "index.tsx"),
    build: path.resolve(__dirname, "build"),
    html: path.resolve(__dirname, "public", "index.html"),
    src: path.resolve(__dirname, "src"),
  };

  const mode = env.mode || "development";
  const isDev = mode === "development";
  const PORT = env.port || 3000;
  const restBaseUrl = env.restBaseUrl || `${API_ENDPOINT_HOST}:${API_ENDPOINT_HOST_PORT}`;

  const config: webpack.Configuration = buildWebpackConfig({
    mode,
    paths,
    isDev,
    port: PORT,
    restBaseUrl
  });

  return config;
};

import webpack, { DefinePlugin, RuleSetRule } from "webpack";
import path from "path";
import { buildCssLoader, buildSVGLoader } from "../build/buildLoaders";
import { BuildPaths } from "../build/types/config";

export default ({ config }: {config: webpack.Configuration}): webpack.Configuration => {
  if (!config.resolve?.modules || !config.resolve?.extensions || !config.module?.rules) {
    return config;
  }

  const paths: BuildPaths = {
    build: "",
    html: "",
    entry: "",
    src: path.resolve(__dirname, "..", "..", "src"),
  };

  config.resolve.modules.push(paths.src);
  config.resolve.extensions.push(".ts", ".tsx");

  // eslint-disable-next-line no-param-reassign
  config.module.rules = config.module.rules.map((rule: any) => { // rule: RuleSetRule
    if (/svg/.test(rule.test as string)) {
      return { ...rule, exclude: /\.svg$/i };
    }

    return rule;
  });

  config.module.rules.push(buildSVGLoader());

  config.module.rules.push(buildCssLoader(true));

  if (config && config.plugins) {
    config.plugins.push(
      new DefinePlugin({
        __IS_DEV__: true,
        __REST_API__BASE_URL__: JSON.stringify(""),
      })
    );
  }

  return config;
};

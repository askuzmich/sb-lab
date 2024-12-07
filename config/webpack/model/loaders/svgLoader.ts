export function svgLoader() {
  return {
    test: /\.svg$/,
    use: ["@svgr/webpack"]
  };
}

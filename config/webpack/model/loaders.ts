import webpack from "webpack";
import { IOptions } from "../types";
import { svgLoader } from "./loaders/svgLoader";
import { cssLoader } from "./loaders/cssLoader";
import { fileLoader } from "./loaders/fileLoader";
import { typescriptLoader } from "./loaders/tsLoader";

export function loaders({ isDev }: IOptions): webpack.RuleSetRule[] {
  return [
    typescriptLoader,
    cssLoader(isDev),
    svgLoader(),
    fileLoader
  ];
}

export type Mods = Record<string, string | boolean | undefined>;

export const classes = (
  mainClass: string,
  mods: Mods = {},
  classesArr: Array<string | undefined> = []
): string =>
  [
    mainClass,
    ...classesArr.filter(Boolean),
    ...Object.entries(mods)
      .filter(([key, value]) => Boolean(value))
      .map(([key, value]) => key),
  ].join(" ");

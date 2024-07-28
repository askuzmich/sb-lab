type Mods = Record<string, string | boolean>;

export const classes = (
  mainClass: string,
  mods: Mods = {},
  additional: string[] = []
): string =>
  [
    mainClass,
    ...additional.filter(Boolean),
    Object.entries(mods)
      .filter(([key, value]) => Boolean(value))
      .map(([key, value]) => key),
  ].join(" ");

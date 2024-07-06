type Mods = Record<string, string | boolean>;

export const classes = (
  mainClass: string,
  mods: Mods,
  additional: string[]
): string => {
  return [
    mainClass,
    ...additional,
    Object.entries(mods)
      .filter(([key, value]) => Boolean(value))
      .map(([key, value]) => key),
  ].join(" ");
};

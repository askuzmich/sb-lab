import { classes, Mods } from "shared/lib/classNames/classes";
import { memo } from "react";
import cls from "./Text.module.scss";

export enum TextTheme {
  PRIMARY = "primary",
  INVERTED = "inverted",
  LIGHT = "light",
  ERROR = "error",
}

export enum TextAlign {
  CENTER = "center",
  RIGHT = "right",
  LEFT = "left",
}

interface ITextProps {
  className?: string;
  title?: string;
  text?: string;
  theme?: TextTheme;
  textAlign?: TextAlign;
}

export const Text = memo(({
  className, text, title, theme = TextTheme.PRIMARY, textAlign = TextAlign.CENTER
}: ITextProps) => {
  const mods: Mods = {
    [cls[theme]]: true,
    [cls[textAlign]]: true,
  };

  return (
    <div className={classes(cls.Text, mods, [className])}>
      {title && <p className={cls.title}>{title}</p>}
      {text && <p className={cls.text}>{text}</p>}
    </div>
  );
});

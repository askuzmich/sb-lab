import { ButtonHTMLAttributes, FC } from "react";
import { classes } from "shared/lib/classNames/classes";
import cls from "./Button.module.scss";

export enum ButtonTheme {
  CLEAR = "clear",
  GRAY = "gray",
  GREEN = "green",
  RED = "red",
  GRAY_OUTLINE = "grayOutline"
}

export enum ButtonSize {
  M = "size_m",
  L = "size_l",
  XL = "size_xl"
}

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  className?: string;
  theme?: ButtonTheme;
  size?: ButtonSize;
}

export const Button: FC<ButtonProps> = (props) => {
  const { className, children, theme, size = ButtonSize.M, ...otherProps } = props;

  const mods: Record<string, boolean> = {
    [cls[size]]: true
  };

  return (
    <button
      type="button"
      className={classes(cls.Button, mods, [cls[theme], className])}
      {...otherProps}
    >
      {children}
    </button>
  );
};

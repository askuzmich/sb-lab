import { ButtonHTMLAttributes, FC, memo, ReactNode } from "react";
import { classes, Mods } from "shared/lib/classNames/classes";
import cls from "./Button.module.scss";

export enum ButtonTheme {
  CLEAR = "clear",
  CLEAR_PAD = "clearPad",
  GRAY = "gray",
  GREEN = "green",
  RED = "red",
  GRAY_OUTLINE = "grayOutline",
  WHITE_OUTLINE = "whiteOutline"
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
  disabled?: boolean;
  children?: ReactNode;
}

export const Button = memo((props: ButtonProps) => {
  const { className, children, theme = ButtonTheme.GRAY_OUTLINE, disabled, size = ButtonSize.M, ...otherProps } = props;

  const mods: Mods = {
    [cls[size]]: true,
    [cls[theme]]: true,
    [cls.disabled]: disabled,
  };

  return (
    <button
      type="button"
      className={classes(cls.Button, mods, [cls[theme], className])}
      disabled={disabled}
      {...otherProps}
    >
      {children}
    </button>
  );
});

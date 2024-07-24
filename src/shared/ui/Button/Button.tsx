import { classes } from "shared/lib/classNames/classes";
import cls from "./Button.module.scss";
import { ButtonHTMLAttributes, FC } from "react";
export enum ButtonTheme {
  CLEAR = "clear",
  GREEN = "green",
}

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  className?: string;
  theme?: ButtonTheme;
}

export const Button: FC<ButtonProps> = (props) => {
  const { className, children, theme, ...otherProps } = props;

  return (
    <button
      className={classes(cls.Button, {}, [cls[theme], className])}
      {...otherProps}
    >
      {children}
    </button>
  );
};
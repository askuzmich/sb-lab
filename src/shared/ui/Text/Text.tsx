import { classes } from "shared/lib/classNames/classes";
import cls from "./Text.module.scss";

export enum TextTheme {
  PRIMARY = "lightBackground",
  ERROR = "error",
}

interface ITextProps {
  className?: string;
  title?: string;
  text?: string;
  theme?: TextTheme;
}

export const Text = ({ className, text, title, theme = TextTheme.PRIMARY }: ITextProps) => {
  return (
    <div className={classes(cls.Text, { [cls[theme]]: true }, [className])}>
      {title && <p className={cls.title}>{title}</p>}
      {text && <p className={cls.text}>{text}</p>}
    </div>
  );
};

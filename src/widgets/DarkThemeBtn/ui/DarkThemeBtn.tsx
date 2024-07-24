import { classes } from "shared/lib/classNames/classes";
import cls from "./DarkThemeBtn.module.scss";
import { useTheme } from "app/providers/ThemeProvider";
import { Button, ButtonTheme } from "shared/ui/Button/Button";

interface DarkThemeBtnProps {
  className?: string;
}

export const DarkThemeBtn = ({ className }: DarkThemeBtnProps) => {
  const { theme, toggleTheme } = useTheme();

  return (
    <Button
      theme={ButtonTheme.GREEN}
      className={classes(cls.DarkThemeBtn, {}, [className])}
      onClick={toggleTheme}
    >
      Theme
    </Button>
  );
};

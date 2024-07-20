import { classes } from "shared/lib/classNames/classes";
import cls from "./Navbar.module.scss";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";

interface NavbarProps {
  className?: string;
}

export const Navbar = ({ className }: NavbarProps) => {
  return (
    <div className={classes(cls.Navbar, {}, [className])}>
      <div className={cls.links}>
        <AppLink
          theme={AppLinkTheme.SECONDARY}
          className={cls.appLink}
          to={"/"}
        >
          Главная
        </AppLink>
        <AppLink
          theme={AppLinkTheme.SECONDARY}
          className={cls.appLink}
          to={"/about"}
        >
          О проекте
        </AppLink>
        <AppLink
          theme={AppLinkTheme.SECONDARY}
          className={cls.appLink}
          to={"/auth"}
        >
          Регистрация
        </AppLink>
      </div>
    </div>
  );
};

import { classes } from "shared/lib/classNames/classes";
import cls from "./Navbar.module.scss";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";
import { useTranslation } from "react-i18next";

interface NavbarProps {
  className?: string;
}

export const Navbar = ({ className }: NavbarProps) => {
  const { t } = useTranslation();

  return (
    <div className={classes(cls.Navbar, {}, [className])}>
      <div className={cls.links}>
        <AppLink
          theme={AppLinkTheme.SECONDARY}
          className={cls.appLink}
          to={"/"}
        >
          {t("Главная")}
        </AppLink>
        <AppLink
          theme={AppLinkTheme.SECONDARY}
          className={cls.appLink}
          to={"/about"}
        >
          {t("О проекте")}
        </AppLink>
        <AppLink
          theme={AppLinkTheme.SECONDARY}
          className={cls.appLink}
          to={"/auth"}
        >
          {t("Регистрация")}
        </AppLink>
      </div>
    </div>
  );
};

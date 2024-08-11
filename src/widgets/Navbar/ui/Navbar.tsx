import { classes } from "shared/lib/classNames/classes";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";
import { useTranslation } from "react-i18next";
import { RoutePath } from "shared/config/routeConfig/routeConfig";
import cls from "./Navbar.module.scss";

interface NavbarProps {
  className?: string;
}

export const Navbar = ({ className }: NavbarProps) => {
  const { t } = useTranslation();

  return (
    <div className={classes(cls.Navbar, {}, [className])}>
      <div className={cls.links}>
        <AppLink theme={AppLinkTheme.SECONDARY} className={cls.appLink} to={RoutePath.main}>
          {t("Главная")}
        </AppLink>
        <AppLink
          theme={AppLinkTheme.SECONDARY}
          className={cls.appLink}
          to={RoutePath.auth}
        >
          {t("Регистрация")}
        </AppLink>
      </div>
    </div>
  );
};

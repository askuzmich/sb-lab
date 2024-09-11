/* eslint-disable max-len */
import { classes } from "shared/lib/classNames/classes";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";
import { useTranslation } from "react-i18next";
import { RoutePath } from "shared/config/routeConfig/routeConfig";
import { ModalWin } from "shared/ui/ModalWin/ModalWin";
import { useCallback, useState } from "react";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import cls from "./Navbar.module.scss";

interface NavbarProps {
  className?: string;
}

export const Navbar = ({ className }: NavbarProps) => {
  const { t } = useTranslation();

  const [isAuthModalWinOpen, setAuthModalWinOpen] = useState(false);

  const onAuthModalToggle = useCallback(() => {
    setAuthModalWinOpen((prev) => !prev);
  }, []);

  return (
    <div className={classes(cls.Navbar, {}, [className])}>
      <div className={cls.links}>
        <AppLink theme={AppLinkTheme.SECONDARY} className={cls.appLink} to={RoutePath.main}>
          {t("Главная")}
        </AppLink>

        <Button
          theme={ButtonTheme.WHITE_OUTLINE}
          className={classes(cls.DarkThemeBtn, {}, [className])}
          onClick={onAuthModalToggle}
        >
          {t("Регистрация")}
        </Button>

        <ModalWin isOpen={isAuthModalWinOpen} onClose={onAuthModalToggle}>
          {t("lorem")}
        </ModalWin>
      </div>
    </div>
  );
};

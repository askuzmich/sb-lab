/* eslint-disable max-len */
import { classes } from "shared/lib/classNames/classes";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";
import { useTranslation } from "react-i18next";
import { RoutePath } from "shared/config/routeConfig/routeConfig";
import { ModalWin } from "shared/ui/ModalWin/ModalWin";
import { useCallback, useState } from "react";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { LoginModal } from "features/AuthByUserName";
import { useDispatch, useSelector } from "react-redux";
import { getUserAuthData, userActions } from "entities/User";
import cls from "./Navbar.module.scss";

interface NavbarProps {
  className?: string;
}

export const Navbar = ({ className }: NavbarProps) => {
  const { t } = useTranslation();

  const [isAuthModalWinOpen, setAuthModalWin] = useState(false);

  const authData = useSelector(getUserAuthData);

  const dispatch = useDispatch();

  const onAuthModalClose = useCallback(() => {
    setAuthModalWin(false);
  }, []);

  const onAuthModalOpen = useCallback(() => {
    setAuthModalWin(true);
  }, []);

  const onLogout = useCallback(() => {
    dispatch(userActions.logout());
  }, [dispatch]);

  if (authData) {
    return (
      <div className={classes(cls.Navbar, {}, [className])}>
        <div className={cls.links}>
          <AppLink theme={AppLinkTheme.SECONDARY} className={cls.appLink} to={RoutePath.main}>
            {t("Главная")}
          </AppLink>

          <Button
            theme={ButtonTheme.WHITE_OUTLINE}
            className={classes(cls.DarkThemeBtn, {}, [className])}
            onClick={onLogout}
          >
            {t("Выйти")}
          </Button>
        </div>
      </div>
    );
  }

  return (
    <div className={classes(cls.Navbar, {}, [className])}>
      <div className={cls.links}>
        <AppLink theme={AppLinkTheme.SECONDARY} className={cls.appLink} to={RoutePath.main}>
          {t("Главная")}
        </AppLink>

        <Button
          theme={ButtonTheme.WHITE_OUTLINE}
          className={classes(cls.DarkThemeBtn, {}, [className])}
          onClick={onAuthModalOpen}
        >
          {t("Регистрация")}
        </Button>
        <LoginModal isOpen={isAuthModalWinOpen} onClose={onAuthModalClose} />
      </div>
    </div>
  );
};

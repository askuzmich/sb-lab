/* eslint-disable max-len */
import { classes } from "shared/lib/classNames/classes";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";
import { useTranslation } from "react-i18next";
import { RoutePath } from "resources/config/routeConfig/routeConfig";
import { ModalWin } from "shared/ui/ModalWin/ModalWin";
import { memo, useCallback, useMemo, useState } from "react";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { LoginModal } from "features/AuthByUserName";
import { useDispatch, useSelector } from "react-redux";
import UserProfileSVG from "shared/assets/icons/user-profile.svg";

import { getUserAuthData, userActions } from "entities/User";
import { Theme, useTheme } from "app/providers/ThemeProvider";
import cls from "./Navbar.module.scss";
import { SidebarItemsList } from "../NavbarItem/NavbarItemsList";
import { INavbarItem } from "../NavbarItem/types/INavbarItem";
import { NavbarItem } from "../NavbarItem/NavbarItem";

interface NavbarProps {
  className?: string;
}

export const Navbar = memo(({ className }: NavbarProps) => {
  const { t } = useTranslation();

  const { theme } = useTheme();

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

  const NavbarItemList = SidebarItemsList.map((item: INavbarItem) => <NavbarItem key={item.path} item={item} />);

  if (authData) {
    return (
      <div className={classes(cls.Navbar, {}, [className])}>
        <div className={cls.links}>

          { NavbarItemList }

          <Button
            theme={theme === Theme.DARK ? ButtonTheme.WHITE_OUTLINE : ButtonTheme.GRAY_OUTLINE}
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

        { NavbarItemList }

        <Button
          data-testid="toggle-navbar-btn"
          className={classes(cls.sidebarBtn)}
          theme={ButtonTheme.CLEAR_PAD}
          type="button"
          onClick={onAuthModalOpen}
        >
          <UserProfileSVG
            width={22}
            height={22}
            fill={theme === Theme.DARK ? "#fff" : "#000"}
          />
        </Button>

        {isAuthModalWinOpen && <LoginModal isOpen={isAuthModalWinOpen} onClose={onAuthModalClose} />}

      </div>
    </div>
  );
});
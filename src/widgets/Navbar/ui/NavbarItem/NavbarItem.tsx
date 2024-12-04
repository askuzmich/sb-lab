import { useTranslation } from "react-i18next";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";
import { memo } from "react";
import { useSelector } from "react-redux";
import { getUserAuthData } from "entities/User";
import { INavbarItem } from "./types/INavbarItem";
import cls from "./NavbarItem.module.scss";

interface INavbarItemProps {
  item: INavbarItem;
  collapsed?: boolean;
  authOnly?: boolean;
}

export const NavbarItem = memo(({ item, collapsed, authOnly }: INavbarItemProps) => {
  const { t } = useTranslation();

  const isAuth = useSelector(getUserAuthData);

  if (item.authOnly && !isAuth) {
    return null;
  }

  return (
    <AppLink theme={AppLinkTheme.SECONDARY} className={cls.appLink} to={item.path}>
      {t(item.text)}
    </AppLink>
  );
});

import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";
import { RoutePath } from "resources/config/routeConfig/routeConfig";
import { memo } from "react";
import cls from "./NavbarItem.module.scss";
import { INavbarItem } from "./types/INavbarItem";

interface NavbarItemProps {
  item?: INavbarItem;
  collapsed?: boolean;
}

export const NavbarItem = memo(({ item, collapsed }: NavbarItemProps) => {
  const { t } = useTranslation();

  return (
    <AppLink theme={AppLinkTheme.SECONDARY} className={cls.appLink} to={item.path}>
      {t(item.text)}
    </AppLink>
  );
});

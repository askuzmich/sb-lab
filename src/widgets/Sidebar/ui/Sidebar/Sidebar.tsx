import { classes } from "shared/lib/classNames/classes";
import { useState } from "react";
import { DarkThemeBtn } from "features/DarkThemeBtn";

import SettingsLogo from "shared/assets/images/settings-dark.png";
import { ChangeLangBtn } from "features/ChangeLangBtn";

import { Button, ButtonSize, ButtonTheme } from "shared/ui/Button/Button";
import { useTranslation } from "react-i18next";
import { AppLink, AppLinkTheme } from "shared/ui/AppLink/AppLink";
import { RoutePath } from "shared/config/routeConfig/routeConfig";
import cls from "./Sidebar.module.scss";

interface SidebarProps {
  className?: string;
}

export const Sidebar = ({ className }: SidebarProps) => {
  const [collapsed, setCollapsed] = useState(true);

  const [t] = useTranslation();

  const onToggle = () => {
    setCollapsed((prev) => !prev);
  };

  return (
    <div
      data-testid="sidebar"
      className={classes(cls.Sidebar, { [cls.collapsed]: collapsed }, [
        className,
      ])}
    >
      <Button
        data-testid="toggle-sidebar-btn"
        className={classes(cls.sidebarBtn)}
        size={ButtonSize.M}
        theme={ButtonTheme.GRAY}
        type="button"
        onClick={onToggle}
      >
        <img
          alt={t("логотип настроек")}
          className={cls.buttonImage}
          src={SettingsLogo}
          width={20}
          height={20}
        />
      </Button>

      <div
        className={classes(cls.sidebarContent, { [cls.clear]: collapsed }, [])}
      >
        <div className={cls.links}>
          <AppLink
            theme={AppLinkTheme.SECONDARY}
            className={cls.sideLink}
            to={RoutePath.about}
          >
            {t("О проекте")}
          </AppLink>
        </div>
        <DarkThemeBtn className={cls.sidebarSpacing} />
        <ChangeLangBtn className={cls.sidebarSpacing} />
      </div>
    </div>
  );
};

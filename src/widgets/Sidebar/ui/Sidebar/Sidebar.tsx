import { classes } from "shared/lib/classNames/classes";
import { useState } from "react";
import { DarkThemeBtn } from "features/DarkThemeBtn";

import SettingsLogo from "shared/assets/images/settings-dark.png";
import { ChangeLangBtn } from "features/ChangeLangBtn";

import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { useTranslation } from "react-i18next";
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
        <DarkThemeBtn className={cls.sidebarSpacing} />
        <ChangeLangBtn className={cls.sidebarSpacing} />
      </div>
    </div>
  );
};

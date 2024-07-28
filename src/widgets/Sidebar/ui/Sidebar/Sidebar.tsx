import { classes } from "shared/lib/classNames/classes";
import { useState } from "react";
import { DarkThemeBtn } from "widgets/DarkThemeBtn";

import SettingsLogo from "shared/assets/images/settings-dark.png";
import { ChangeLangBtn } from "widgets/ChangeLangBtn";

import { Button, ButtonTheme } from "shared/ui/Button/Button";
import cls from "./Sidebar.module.scss";

interface SidebarProps {
  className?: string;
}

export const Sidebar = ({ className }: SidebarProps) => {
  const [collapsed, setCollapsed] = useState(false);

  const onToggle = () => {
    setCollapsed((prev) => !prev);
  };

  return (
    <div
      className={classes(cls.Sidebar, { [cls.collapsed]: collapsed }, [
        className,
      ])}
    >
      <Button theme={ButtonTheme.CLEAR} type="button" onClick={onToggle}>
        <img alt="settsLogo" src={SettingsLogo} width={20} height={20} />
      </Button>

      <div
        className={classes(cls.sidebarContent, { [cls.clear]: collapsed }, [])}
      >
        <br />
        <br />
        <DarkThemeBtn />
        <br />
        <br />
        <ChangeLangBtn />
      </div>
    </div>
  );
};

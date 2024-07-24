import { classes } from "shared/lib/classNames/classes";
import cls from "./Sidebar.module.scss";
import { useState } from "react";
import { DarkThemeBtn } from "widgets/DarkThemeBtn";

import SettingsLogo from "shared/assets/images/settings-dark.png";
import { ChangeLangBtn } from "widgets/ChangeLangBtn";

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
      <img src={SettingsLogo} onClick={onToggle} width={20} height={20} />

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

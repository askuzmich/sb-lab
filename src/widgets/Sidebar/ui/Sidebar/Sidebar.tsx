import { classes } from "shared/lib/classNames/classes";
import cls from "./Sidebar.module.scss";
import { useState } from "react";
import { DarkThemeBtn } from "widgets/DarkThemeBtn";
import { Button, ButtonTheme } from "shared/ui/Button/Button";

import SettingsLogo from "shared/assets/images/settings-dark.png";

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
        <DarkThemeBtn />
        <br />
        <br />
        <Button
          theme={ButtonTheme.GREEN}
          className={classes(cls.DarkThemeBtn, {}, [className])}
        >
          lang
        </Button>
      </div>
    </div>
  );
};

import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { Theme, useTheme } from "app/providers/ThemeProvider";
import GitLogo from "shared/assets/images/github-mark.png";
import GitLogoLight from "shared/assets/images/github-mark-white.png";
import cls from "./Footer.module.scss";

interface FooterProps {
  className?: string;
}

export const Footer = ({ className }: FooterProps) => {
  const { t } = useTranslation();
  const { theme } = useTheme();

  return (
    <div className={classes(cls.Footer, {}, [className])}>
      <img
        src={theme === Theme.DARK ? GitLogoLight : GitLogo}
        alt=""
        width={30}
        height={30}
      />
    </div>
  );
};

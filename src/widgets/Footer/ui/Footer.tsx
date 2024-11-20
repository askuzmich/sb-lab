import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import GitSVG from "shared/assets/icons/git.svg";
import cls from "./Footer.module.scss";

interface FooterProps {
  className?: string;
}

export const Footer = ({ className }: FooterProps) => {
  const { t } = useTranslation();

  return (
    <div className={classes(cls.Footer, {}, [className])}>
      {/* <img
        src={GitLogoLight}
        alt=""
        width={15}
        height={15}
      /> */}
      <GitSVG
        width={20}
        height={20}
        fill="#777"
      />
    </div>
  );
};

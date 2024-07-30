import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import cls from "./Page404.module.scss";

interface Page404Props {
  className?: string;
}

export const Page404 = ({ className }: Page404Props) => {
  const { t } = useTranslation();

  return (<div className={classes(cls.Page404, {}, [className])}>{t("404")}</div>);
};

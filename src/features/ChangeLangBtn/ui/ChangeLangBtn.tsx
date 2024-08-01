import { useTranslation } from "react-i18next";
import { classes } from "shared/lib/classNames/classes";
import { Button, ButtonTheme } from "shared/ui/Button/Button";

import cls from "./ChangeLangBtn.module.scss";

interface ChangeLangBtnProps {
  className?: string;
}

export const ChangeLangBtn = ({ className }: ChangeLangBtnProps) => {
  const { t, i18n } = useTranslation();

  const changeLang = async () => {
    i18n.changeLanguage(i18n.language === "ru" ? "en" : "ru");
  };

  return (
    <Button
      theme={ButtonTheme.GREEN}
      className={classes(cls.DarkThemeBtn, {}, [className])}
      onClick={changeLang}
    >
      {t("язык")}
    </Button>
  );
};

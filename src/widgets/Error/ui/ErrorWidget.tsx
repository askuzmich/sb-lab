import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import cls from "./ErrorWidget.module.scss";

interface ErrorWidgetProps {
  className?: string;
}

export const ErrorWidget = ({ className }: ErrorWidgetProps) => {
  const { t } = useTranslation();

  const reload = () => {
    // eslint-disable-next-line no-restricted-globals
    location.reload();
  };

  return (
    <div className={classes(cls.ErrorWidget, {}, [className])}>
      <h1>{t("ошибка")}</h1>
      <Button theme={ButtonTheme.GREEN} onClick={reload}>{t("перезагрузить")}</Button>
    </div>
  );
};

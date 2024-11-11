import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { Input } from "shared/ui/Input/Input";
import cls from "./LoginForm.module.scss";

interface LoginFormProps {
  className?: string;
}

export const LoginForm = ({ className }: LoginFormProps) => {
  const { t } = useTranslation();

  return (
    <div className={classes(cls.LoginForm, {}, [className])}>
      <Input placeholder={t("имя пользователя")} className={cls.input} />
      <Input placeholder={t("пароль")} type="password" />
      <Button theme={ButtonTheme.GREEN} className={cls.loginBtn}>{t("войти")}</Button>
    </div>
  );
};

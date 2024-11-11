import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { Button } from "shared/ui/Button/Button";
import { Input } from "shared/ui/Input/Input";
import cls from "./LoginForm.module.scss";

interface LoginFormProps {
  className?: string;
}

export const LoginForm = ({ className }: LoginFormProps) => {
  const { t } = useTranslation();

  return (
    <div className={classes(cls.LoginForm, {}, [className])}>
      <div className="username">{t("имя пользователя")}</div>
      <Input type="text" className={cls.input} />
      <div className="pass">{t("пароль")}</div>
      <Input type="text" className={cls.input} />
      <Button className={cls.loginBtn}>{t("войти")}</Button>
    </div>
  );
};

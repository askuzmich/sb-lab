import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { Input } from "shared/ui/Input/Input";
import { useDispatch, useSelector } from "react-redux";
import { memo, useCallback } from "react";
import { loginActions } from "../../model/slice/loginSlice";
import cls from "./LoginForm.module.scss";
import { getLoginState } from "../../model/selectors/getLoginState/getLoginState";

interface LoginFormProps {
  className?: string;
}

export const LoginForm = memo(({ className }: LoginFormProps) => {
  const { t } = useTranslation();

  const dispatch = useDispatch();

  const loginForm = useSelector(getLoginState);

  const onChangeName = useCallback((val: string) => {
    dispatch(loginActions.setUsername(val));
  }, [dispatch]);

  const onChangePass = useCallback((val: string) => {
    dispatch(loginActions.setPassword(val));
  }, [dispatch]);

  const onFormCommit = useCallback(() => {
  }, []);

  return (
    <div className={classes(cls.LoginForm, {}, [className])}>
      <Input
        placeholder={t("имя пользователя")}
        onChange={onChangeName}
        className={cls.input}
        value={loginForm.username}
      />
      <Input
        placeholder={t("пароль")}
        onChange={onChangePass}
        type="password"
        value={loginForm.password}
      />
      <Button
        theme={ButtonTheme.GREEN}
        className={cls.loginBtn}
        onClick={onFormCommit}
      >
        {t("войти")}
      </Button>
    </div>
  );
});

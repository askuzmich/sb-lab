import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { Input } from "shared/ui/Input/Input";
import { useDispatch, useSelector } from "react-redux";
import { memo, useCallback } from "react";
import { Text, TextTheme } from "shared/ui/Text/Text";
import { loginByUsername } from "../../model/service/loginByUsername/loginByUsername";
import { loginActions } from "../../model/slice/loginSlice";
import cls from "./LoginForm.module.scss";
import { getLoginState } from "../../model/selectors/getLoginState/getLoginState";

interface LoginFormProps {
  className?: string;
}

export const LoginForm = memo(({ className }: LoginFormProps) => {
  const { t } = useTranslation();

  const dispatch = useDispatch();

  const { username, password, error, isLoading } = useSelector(getLoginState);

  const onChangeName = useCallback((val: string) => {
    dispatch(loginActions.setUsername(val));
  }, [dispatch]);

  const onChangePass = useCallback((val: string) => {
    dispatch(loginActions.setPassword(val));
  }, [dispatch]);

  const onFormCommit = useCallback(() => {
    dispatch(loginByUsername({
      username,
      password
    }));
  }, [dispatch, password, username]);

  return (
    <div className={classes(cls.LoginForm, {}, [className])}>
      <Text title={t("Регистрация")} theme={TextTheme.PRIMARY} />

      {error && <Text text={t("ошибка загрузки")} theme={TextTheme.ERROR} />}

      <Input
        placeholder={t("имя пользователя")}
        onChange={onChangeName}
        className={cls.input}
        value={username}
      />
      <Input
        placeholder={t("пароль")}
        onChange={onChangePass}
        type="password"
        value={password}
      />
      <Button
        theme={ButtonTheme.GREEN}
        className={cls.loginBtn}
        onClick={onFormCommit}
        disabled={isLoading}
      >
        {t("войти")}
      </Button>
    </div>
  );
});

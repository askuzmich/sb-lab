import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { Input } from "shared/ui/Input/Input";
import { useDispatch, useSelector, useStore } from "react-redux";
import { memo, useCallback, useEffect } from "react";
import { Text, TextTheme } from "shared/ui/Text/Text";
import { IStoreManager } from "app/providers/StoreProvider";
import { getLoginUsername } from "../../model/selectors/getLoginUsername/getLoginUsername";
import { getLoginPassword } from "../../model/selectors/getLoginPassword/getLoginPassword";
import { getLoginError } from "../../model/selectors/getLoginError/getLoginError";
import { getLoginIsLoading } from "../../model/selectors/getLoginIsLoading/getLoginIsLoading";
import { loginByUsername } from "../../model/service/loginByUsername/loginByUsername";
import { loginActions, loginReducer } from "../../model/slice/loginSlice";
import cls from "./LoginForm.module.scss";

interface LoginFormProps {
  className?: string;
}

const LoginForm = memo(({ className }: LoginFormProps) => {
  const { t } = useTranslation();

  const dispatch = useDispatch();

  const store = useStore() as IStoreManager;

  const username = useSelector(getLoginUsername);
  const password = useSelector(getLoginPassword);
  const error = useSelector(getLoginError);
  const isLoading = useSelector(getLoginIsLoading);

  useEffect(() => {
    store.reducerManager.add("loginForm", loginReducer);
    dispatch({ type: "@INIT loginform rdcr" });

    return () => {
      store.reducerManager.remove("loginForm");

      dispatch({ type: "@INIT loginform rdcr" });

      dispatch({ type: "@REM loginform rdcr" });
    };

    // eslint-disable-next-line
  }, []);

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

      {error && <Text text={error} theme={TextTheme.ERROR} />}

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

export default LoginForm;

import React from "react";
import { useTheme } from "app/providers/ThemeProvider";
import { useTranslation } from "react-i18next";
import { Counter } from "entities/Counter";
import { AsyncModule, ReducerListT } from "shared/lib/AsyncModule/AsyncModule";
import { profileReducer } from "entities/Profile";

const reducers: ReducerListT = {
  profile: profileReducer
};

const ProfilePage = () => {
  const { theme } = useTheme();

  const [t] = useTranslation("profile");

  return (
    <AsyncModule reducers={reducers}>
      <div>
        <h2 className="App-link">{t("профиль пользователя")}</h2>
        <Counter />
      </div>
    </AsyncModule>
  );
};

export default ProfilePage;

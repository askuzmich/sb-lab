import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import { Counter } from "entities/Counter";
import { AsyncModule, ReducerListT } from "shared/lib/AsyncModule/AsyncModule";
import { fetchProfile, ProfileCard, profileReducer } from "entities/Profile";
import { useAppDispatch } from "shared/lib/hooks/useAppDispatch";

const reducers: ReducerListT = {
  profile: profileReducer
};

const ProfilePage = () => {
  // const { theme } = useTheme();
  const [t] = useTranslation("profile");

  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(fetchProfile({ profileId: 1 }));
  }, [dispatch]);

  return (
    <AsyncModule reducers={reducers}>
      <div>
        <h2 className="App-link">{t("профиль пользователя")}</h2>
        <ProfileCard />
        <Counter />
      </div>
    </AsyncModule>
  );
};

export default ProfilePage;

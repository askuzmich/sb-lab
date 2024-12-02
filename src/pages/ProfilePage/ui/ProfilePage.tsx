import React, { useCallback, useEffect } from "react";
import { useTranslation } from "react-i18next";
import { AsyncModule, ReducerListT } from "shared/lib/AsyncModule/AsyncModule";
import {
  EnumValidateProfileErrs,
  fetchProfile, getProfile,
  getProfileEdited,
  getProfileErr, getProfileIsLoading,
  getProfileIsReadOnly, getProfileValidateErr, profileActions, ProfileCard, profileReducer
} from "entities/Profile";
import { useAppDispatch } from "shared/lib/hooks/useAppDispatch";
import { useSelector } from "react-redux";
import { ECurrency } from "entities/Currency";
import { ECountry } from "entities/Country";
import { Text, TextTheme } from "shared/ui/Text/Text";
import { Header } from "./Header/Header";
import { Footer } from "./Footer/Footer";

const reducers: ReducerListT = {
  profile: profileReducer
};

const ProfilePage = () => {
  // const { theme } = useTheme();
  const [t] = useTranslation("profile");

  const profileCardData = useSelector(getProfileEdited);

  const isLoading = useSelector(getProfileIsLoading);

  const isReadOnly = useSelector(getProfileIsReadOnly);

  const error = useSelector(getProfileErr);

  const validateErr = useSelector(getProfileValidateErr);

  const dispatch = useAppDispatch();

  const errTranslateMapping = {
    [EnumValidateProfileErrs.CLIENT_ERROR]: t("Ошибка на стороне пользователя"),
    [EnumValidateProfileErrs.SERVER_ERROR]: t("Ошибка на стороне сервера"),
    [EnumValidateProfileErrs.NO_DATA]: t("Нет данных"),
    [EnumValidateProfileErrs.INCORRECT_USER_DATA]: t("Имя и фамилия"),
    [EnumValidateProfileErrs.INCORRECT_AGE]: t("Возраст"),
    [EnumValidateProfileErrs.INCORRECT_COUNTRY]: t("Название страны"),
  };

  const onChangeFirstname = useCallback((firstname?: string) => {
    dispatch(profileActions.updateProfile({ firstname }));
  }, [dispatch]);

  const onChangeLastname = useCallback((lastname?: string) => {
    dispatch(profileActions.updateProfile({ lastname }));
  }, [dispatch]);

  const onChangeAge = useCallback((age?: string) => {
    dispatch(profileActions.updateProfile({ age: Number(age || 0) }));
  }, [dispatch]);

  const onChangeCountry = useCallback((country?: ECountry) => {
    dispatch(profileActions.updateProfile({ country }));
  }, [dispatch]);

  const onChangeCurrency = useCallback((currency?: ECurrency) => {
    dispatch(profileActions.updateProfile({ currency }));
  }, [dispatch]);

  const onChangeCity = useCallback((city?: string) => {
    dispatch(profileActions.updateProfile({ city }));
  }, [dispatch]);

  const onChangeImage = useCallback((image?: string) => {
    dispatch(profileActions.updateProfile({ image }));
  }, [dispatch]);

  useEffect(() => {
    dispatch(fetchProfile({ profileId: 1 }));
  }, [dispatch]);

  return (
    <AsyncModule reducers={reducers}>
      <div>
        <h1 className="App-link">{t("Профиль пользователя")}</h1>
        <Header />

        {validateErr?.length && validateErr.map((err) => (
          <Text key={err} theme={TextTheme.ERROR} title={t("Ошибка")} text={errTranslateMapping[err]} />
        ))}

        <ProfileCard
          profileCardData={profileCardData}
          error={error}
          isLoading={isLoading}
          isReadOnly={isReadOnly}
          onChangeFirstname={onChangeFirstname}
          onChangeLastname={onChangeLastname}
          onChangeAge={onChangeAge}
          onChangeImage={onChangeImage}
          onChangeCountry={onChangeCountry}
          onChangeCurrency={onChangeCurrency}
          onChangeCity={onChangeCity}
        />

        <Footer />

      </div>
    </AsyncModule>
  );
};

export default ProfilePage;

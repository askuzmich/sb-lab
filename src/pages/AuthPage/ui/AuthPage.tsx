import React from "react";
import { useTranslation } from "react-i18next";

const AuthPage = () => {
  const { t } = useTranslation();

  return (
    <div>
      <h2 className="App-link">{t("страница регистрации")}</h2>
    </div>
  );
};

export default AuthPage;

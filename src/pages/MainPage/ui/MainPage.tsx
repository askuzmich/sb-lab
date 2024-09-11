import React from "react";
import Logo from "shared/assets/icons/logo.svg";
import { useTheme } from "app/providers/ThemeProvider";
import { useTranslation } from "react-i18next";
import { Counter } from "entities/Counter";

const MainPage = () => {
  const { theme } = useTheme();

  const [t] = useTranslation("main");

  return (
    <div>
      <h2 className="App-link">{t("главная страница")}</h2>
      <Logo width="300px" />
      <Counter />
    </div>
  );
};

export default MainPage;

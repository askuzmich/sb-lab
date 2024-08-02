import React from "react";
import Logo from "shared/assets/icons/logo.svg";
import GitLogo from "shared/assets/images/github-mark.png";
import GitLogoLight from "shared/assets/images/github-mark-white.png";
import { Theme, useTheme } from "app/providers/ThemeProvider";
import { useTranslation } from "react-i18next";

const MainPage = () => {
  const { theme } = useTheme();

  const [t] = useTranslation("main");

  return (
    <div>
      <h2 className="App-link">{t("главная страница")}</h2>

      <Logo width="300px" />
    </div>
  );
};

export default MainPage;

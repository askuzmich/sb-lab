import "./styles/index.scss";

import { useTheme } from "./providers/ThemeProvider";
import { classes } from "shared/lib/classNames/classes";
import { AppRouter } from "./providers/router";
import { Navbar } from "widgets/Navbar";

import { Sidebar } from "widgets/Sidebar";
import { Suspense } from "react";
import { useTranslation } from "react-i18next";

const Comp = () => {
  const { t, i18n } = useTranslation();

  const changeLang = () => {
    i18n.changeLanguage(i18n.language === "ru" ? "en" : "ru");
  };

  return (
    <div>
      <button onClick={changeLang}>{t("язык")}</button>
      <div>{t("тест")}</div>
    </div>
  );
};

const App = () => {
  const { theme } = useTheme();

  return (
    <div className={classes("app", {}, [theme])}>
      <Suspense fallback="">
        <Navbar />
        <Comp />
        <div className="content-page">
          <Sidebar />
          <AppRouter />
        </div>
      </Suspense>
    </div>
  );
};

export default App;

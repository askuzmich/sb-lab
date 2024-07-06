import { Suspense } from "react";
import { Link, Route, Routes } from "react-router-dom";
import "./styles/index.scss";

import { useTheme } from "./providers/ThemeProvider";
import { AboutPage } from "pages/AboutPage";
import { AuthPage } from "pages/AuthPage";
import { MainPage } from "pages/MainPage";
import { classes } from "shared/lib/classNames/classes";

const App = () => {
  const { theme, toggleTheme } = useTheme();

  return (
    <div className={classes("app", {}, [theme])}>
      <button className="btn" onClick={toggleTheme}>
        Theme
      </button>
      <div
        style={{
          display: "flex",
          justifyContent: "space-around",
          maxWidth: 800,
          margin: "auto",
        }}
      >
        <Link className="app-link" to={"/"}>
          Главная
        </Link>
        <Link className="app-link" to={"/about"}>
          О проекте
        </Link>
        <Link className="app-link" to={"/auth"}>
          Регистрация
        </Link>
      </div>
      <Suspense fallback={<div>Loading...</div>}>
        <Routes>
          <Route path={"/about"} element={<AboutPage />} />
          <Route path={"/"} element={<MainPage />} />
          <Route path={"/auth"} element={<AuthPage />} />
        </Routes>
      </Suspense>
    </div>
  );
};

export default App;

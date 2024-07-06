import { Suspense } from "react";
import { Link, Route, Routes } from "react-router-dom";
import "./styles/index.scss";

import { MainPageAsync } from "../pages/MainPage/MainPage.async";

import { AuthPageAsync } from "../pages/AuthPage/AuthPage.async";
import { useTheme } from "./providers/ThemeProvider";
import { AboutPage } from "pages/AboutPage";

const App = () => {
  const { theme, toggleTheme } = useTheme();

  return (
    <div className={`app ${theme}`}>
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
          <Route path={"/"} element={<MainPageAsync />} />
          <Route path={"/auth"} element={<AuthPageAsync />} />
        </Routes>
      </Suspense>
    </div>
  );
};

export default App;

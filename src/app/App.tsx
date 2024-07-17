import { Link } from "react-router-dom";
import "./styles/index.scss";

import { useTheme } from "./providers/ThemeProvider";
import { classes } from "shared/lib/classNames/classes";
import { AppRouter } from "./providers/router";

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
      <AppRouter />
    </div>
  );
};

export default App;

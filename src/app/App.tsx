import "./styles/index.scss";

import { useTheme } from "./providers/ThemeProvider";
import { classes } from "shared/lib/classNames/classes";
import { AppRouter } from "./providers/router";
import { Navbar } from "widgets/Navbar";

const App = () => {
  const { theme, toggleTheme } = useTheme();

  return (
    <div className={classes("app", {}, [theme])}>
      <Navbar />
      <AppRouter />
      <button className="btn" onClick={toggleTheme}>
        Theme
      </button>
    </div>
  );
};

export default App;

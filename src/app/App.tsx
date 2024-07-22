import "./styles/index.scss";

import { useTheme } from "./providers/ThemeProvider";
import { classes } from "shared/lib/classNames/classes";
import { AppRouter } from "./providers/router";
import { Navbar } from "widgets/Navbar";

import { Sidebar } from "widgets/Sidebar";

const App = () => {
  const { theme } = useTheme();

  return (
    <div className={classes("app", {}, [theme])}>
      <Navbar />
      <div className="content-page">
        <Sidebar />
        <AppRouter />
      </div>
    </div>
  );
};

export default App;

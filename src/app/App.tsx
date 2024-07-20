import "./styles/index.scss";

import { useTheme } from "./providers/ThemeProvider";
import { classes } from "shared/lib/classNames/classes";
import { AppRouter } from "./providers/router";
import { Navbar } from "widgets/Navbar";
import { DarkThemeBtn } from "shared/ui/DarkThemeBtn";

import Logo from "shared/assets/icons/logo.svg";
import Img from "shared/assets/images/img.png";

const App = () => {
  const { theme } = useTheme();

  return (
    <div className={classes("app", {}, [theme])}>
      <Navbar />
      <AppRouter />
      <Logo width={"300px"} />
      {/* <Img /> */}
      <DarkThemeBtn />
    </div>
  );
};

export default App;

import "./styles/index.scss";

import { Theme, useTheme } from "./providers/ThemeProvider";
import { classes } from "shared/lib/classNames/classes";
import { AppRouter } from "./providers/router";
import { Navbar } from "widgets/Navbar";
import { DarkThemeBtn } from "shared/ui/DarkThemeBtn";

import Logo from "shared/assets/icons/logo.svg";
import GitLogo from "shared/assets/images/github-mark.png";
import GitLogoLight from "shared/assets/images/github-mark-white.png";

const App = () => {
  const { theme } = useTheme();

  return (
    <div className={classes("app", {}, [theme])}>
      <Navbar />
      <AppRouter />
      <Logo width={"300px"} />
      <br />
      <DarkThemeBtn />
      <br />
      <br />
      <img
        src={theme === Theme.DARK ? GitLogoLight : GitLogo}
        alt=""
        width={30}
        height={30}
      />
    </div>
  );
};

export default App;

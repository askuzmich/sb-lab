import React from "react";
import Logo from "shared/assets/icons/logo.svg";
import GitLogo from "shared/assets/images/github-mark.png";
import GitLogoLight from "shared/assets/images/github-mark-white.png";
import { Theme, useTheme } from "app/providers/ThemeProvider";

const MainPage = () => {
  const { theme } = useTheme();

  return (
    <div>
      <h2 className="App-link">MAIN PAGE</h2>

      <Logo width={"300px"} />
      <br />

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

export default MainPage;

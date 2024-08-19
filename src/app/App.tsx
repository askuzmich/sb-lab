import "./styles/index.scss";

import { classes } from "shared/lib/classNames/classes";
import { Navbar } from "widgets/Navbar";
import { Footer } from "widgets/Footer";

import { Sidebar } from "widgets/Sidebar";
import { Suspense } from "react";

import { AppRouter } from "./providers/router";
import { useTheme } from "./providers/ThemeProvider";
import { StoreProvider } from "./providers/StoreProvider";

const App = () => {
  const { theme } = useTheme();

  return (
    <StoreProvider>
      <div className={classes("app", {}, [theme])}>
        <Suspense fallback="">
          <Navbar />
          <div className="content-page">
            <Sidebar />
            <AppRouter />
          </div>
          <Footer />
        </Suspense>
      </div>
    </StoreProvider>
  );
};

export default App;

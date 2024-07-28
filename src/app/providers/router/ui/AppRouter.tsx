import { Suspense } from "react";
import { Routes, Route } from "react-router-dom";
import { routeConfig } from "shared/config/routeConfig/routeConfig";

export const AppRouter = () => (
  <Suspense fallback={<div>Loading...</div>}>
    <Routes>
      {Object.values(routeConfig).map(({ element, path }) => (
        <Route
          key={path}
          element={<div className="page-wrapper">{element}</div>}
          path={path}
        />
      ))}
      {/* <Route path={"/about"} element={<AboutPage />} />
        <Route path={"/"} element={<MainPage />} />
        <Route path={"/auth"} element={<AuthPage />} /> */}
    </Routes>
  </Suspense>
);

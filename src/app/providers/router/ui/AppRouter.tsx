import { Suspense } from "react";
import { Routes, Route } from "react-router-dom";
import { routeConfig } from "shared/config/routeConfig/routeConfig";

export const AppRouter = () => {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Routes>
        {Object.values(routeConfig).map(({ element, path }) => (
          <Route key={path} element={element} path={path} />
        ))}
        {/* <Route path={"/about"} element={<AboutPage />} />
        <Route path={"/"} element={<MainPage />} />
        <Route path={"/auth"} element={<AuthPage />} /> */}
      </Routes>
    </Suspense>
  );
};
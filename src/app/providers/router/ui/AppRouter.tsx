import { PageLoader } from "entities/PageLoader/PageLoader";
import { Suspense } from "react";
import { Routes, Route } from "react-router-dom";
import { routeConfig } from "resources/config/routeConfig/routeConfig";

export const AppRouter = () => (
  <Suspense fallback={<PageLoader />}>
    <Routes>
      {Object.values(routeConfig).map(({ element, path }) => (
        <Route
          key={path}
          element={<div className="page-wrapper">{element}</div>}
          path={path}
        />
      ))}
    </Routes>
  </Suspense>
);

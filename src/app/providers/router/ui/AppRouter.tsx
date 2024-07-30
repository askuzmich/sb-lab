import { Suspense } from "react";
import { useTranslation } from "react-i18next";
import { Routes, Route } from "react-router-dom";
import { routeConfig } from "shared/config/routeConfig/routeConfig";

export const AppRouter = () => {
  const [t] = useTranslation();

  return (
    <Suspense fallback={(
      <div style={{
        display: "flex",
        position: "relative",
        width: "100%",
        height: "50vh",
        justifyContent: "center",
        alignItems: "center"
      }}
      >
        <div>{t("загрузка...")}</div>
      </div>
    )}
    >
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
};

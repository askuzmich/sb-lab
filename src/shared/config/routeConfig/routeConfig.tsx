import { AboutPage } from "pages/AboutPage";
import { AuthPage } from "pages/AuthPage";
import { MainPage } from "pages/MainPage";
import { Page404 } from "pages/Page404";
import { RouteProps } from "react-router-dom";

export enum AppRoutes {
  MAIN = "main",
  ABOUT = "about",
  AUTH = "auth",
  PAGE_404 = "404"
}

export const RoutePath: Record<AppRoutes, string> = {
  [AppRoutes.MAIN]: "/",
  [AppRoutes.ABOUT]: "/about",
  [AppRoutes.AUTH]: "/auth",
  [AppRoutes.PAGE_404]: "*"
};

export const routeConfig: Record<AppRoutes, RouteProps> = {
  [AppRoutes.MAIN]: { path: RoutePath.main, element: <MainPage /> },
  [AppRoutes.AUTH]: { path: RoutePath.auth, element: <AuthPage /> },
  [AppRoutes.ABOUT]: { path: RoutePath.about, element: <AboutPage /> },
  [AppRoutes.PAGE_404]: { path: RoutePath["404"], element: <Page404 /> },
};

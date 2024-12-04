import { AboutPage } from "pages/AboutPage";
import { AuthPage } from "pages/AuthPage";
import { MainPage } from "pages/MainPage";
import { Page404 } from "pages/Page404";
import { ProfilePage } from "pages/ProfilePage";
import { RouteProps } from "react-router-dom";

type AppRoutesPropsT = RouteProps & {
  authOnly?: boolean;
}

export enum AppRoutes {
  MAIN = "main",
  ABOUT = "about",
  AUTH = "auth",
  PROFILE = "profile",
  PAGE_404 = "404"
}

export const RoutePath: Record<AppRoutes, string> = {
  [AppRoutes.MAIN]: "/",
  [AppRoutes.ABOUT]: "/about",
  [AppRoutes.PROFILE]: "/profile",
  [AppRoutes.AUTH]: "/auth",
  [AppRoutes.PAGE_404]: "*"
};

export const routeConfig: Record<AppRoutes, AppRoutesPropsT> = {
  [AppRoutes.MAIN]: { path: RoutePath.main, element: <MainPage /> },
  [AppRoutes.AUTH]: { path: RoutePath.auth, element: <AuthPage /> },
  [AppRoutes.ABOUT]: { path: RoutePath.about, element: <AboutPage /> },
  [AppRoutes.PROFILE]: { path: RoutePath.profile, element: <ProfilePage />, authOnly: true },
  [AppRoutes.PAGE_404]: { path: RoutePath["404"], element: <Page404 /> },
};

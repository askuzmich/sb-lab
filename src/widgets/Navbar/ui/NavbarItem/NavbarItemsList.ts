import { RoutePath } from "resources/config/routeConfig/routeConfig";
import { INavbarItem } from "./types/INavbarItem";

export const SidebarItemsList: INavbarItem[] = [
  {
    path: RoutePath.main,
    text: "Главная"
  },
  {
    path: RoutePath.about,
    text: "О проекте"
  },
  {
    path: RoutePath.profile,
    text: "Профиль",
    authOnly: true
  }
];

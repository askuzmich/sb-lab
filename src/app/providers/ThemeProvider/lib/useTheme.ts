import { useContext } from "react";
import { LOCAL_STORAGE_THEME_KEY, Theme, ThemeContext } from "./ThemeContext";

interface UseThemeResult {
  toggleTheme: () => void;
  theme: Theme;
}

export function useTheme(): UseThemeResult {
  const { theme, setTheme } = useContext(ThemeContext);

  const toggleTheme = () => {
    // let newTheme: Theme;
    const newTheme = theme === Theme.DARK ? Theme.LIGHT : Theme.DARK;
    // switch (theme) {
    //   case Theme.DARK:
    //     newTheme = Theme.DARK;
    //     break;
    //   case Theme.LIGHT:
    //     newTheme = Theme.LIGHT;
    //     break;
    //   default:
    //     newTheme = Theme.LIGHT;
    // }

    document.body.className = newTheme;
    setTheme?.(newTheme);
    localStorage.setItem(LOCAL_STORAGE_THEME_KEY, newTheme);
  };

  return {
    theme: theme || Theme.DARK,
    toggleTheme,
  };
}

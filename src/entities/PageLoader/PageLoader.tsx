import { classes } from "shared/lib/classNames/classes";
import { Loader } from "shared/ui/Loader/Loader";
import cls from "./PageLoader.module.scss";

interface PageLoaderProps {
  className?: string;
}

export const PageLoader = ({ className }: PageLoaderProps) => (
  <div className={classes(cls.PageLoader, {}, [className])}>
    <Loader />
  </div>
);

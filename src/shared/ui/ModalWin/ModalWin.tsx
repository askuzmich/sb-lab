import { classes } from "shared/lib/classNames/classes";
import { ReactNode, useCallback, useEffect } from "react";
import cls from "./ModalWin.module.scss";
import { Portal } from "../Portal/Portal";

interface ModalWinProps {
  className?: string;
  children?: ReactNode;
  isOpen?: boolean;
  onClose?: () => void;
}

export const ModalWin = (props: ModalWinProps) => {
  const { className, children, isOpen, onClose } = props;

  const winCloseHandler = useCallback(() => {
    if (onClose) {
      onClose();
    }
  }, [onClose]);

  const contentHandler = (e: React.MouseEvent) => {
    e.stopPropagation();
  };

  const mods: Record<string, boolean> = {
    [cls.revealed]: isOpen
  };

  const onWinCloseByESC = useCallback((e: KeyboardEvent) => {
    if (e.key === "Escape") {
      winCloseHandler();
    }
  }, [winCloseHandler]);

  useEffect(() => {
    if (isOpen) {
      window.addEventListener("keydown", onWinCloseByESC);
    }

    // on component unmount
    return () => {
      window.removeEventListener("keydown", onWinCloseByESC);
    };
  }, [isOpen, onWinCloseByESC]);

  return (
    <Portal>
      <div className={classes(cls.ModalWin, mods, [className])}>
        <div className={cls.overlay} onClick={winCloseHandler}>
          <div
            className={cls.content}
            onClick={contentHandler}
          >
            {children}
          </div>
        </div>
      </div>
    </Portal>
  );
};

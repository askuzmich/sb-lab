import { classes } from "shared/lib/classNames/classes";
import { ReactNode } from "react";
import cls from "./ModalWin.module.scss";

interface ModalWinProps {
  className?: string;
  children?: ReactNode;
  isOpen?: boolean;
  onClose?: () => void;
}

export const ModalWin = (props: ModalWinProps) => {
  const { className, children, isOpen, onClose } = props;

  return (
    <div className={classes(cls.ModalWin, {}, [className])}>
      <div className={cls.overlay}>
        <div className={cls.content}>
          {children}
        </div>
      </div>
    </div>
  );
};

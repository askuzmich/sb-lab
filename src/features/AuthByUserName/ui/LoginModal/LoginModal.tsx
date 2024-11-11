import { classes } from "shared/lib/classNames/classes";
import { ModalWin } from "shared/ui/ModalWin/ModalWin";
import cls from "./LoginModal.module.scss";
import { LoginForm } from "../LoginForm/LoginForm";

interface LoginModalProps {
  className?: string;
  isOpen: boolean;
  onClose: () => void;
}

export const LoginModal = ({ className, isOpen, onClose }: LoginModalProps) => {
  return (
    <ModalWin
      isOpen={isOpen}
      onClose={onClose}
      className={classes(cls.LoginModal, {}, [className])}
    >
      <LoginForm />
    </ModalWin>
  );
};

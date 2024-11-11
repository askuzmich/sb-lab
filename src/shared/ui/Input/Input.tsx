import { classes } from "shared/lib/classNames/classes";
import { InputHTMLAttributes, memo } from "react";
import cls from "./Input.module.scss";

type HTMLInputProps = Omit<InputHTMLAttributes<HTMLInputElement>, "value" | "onChange">

interface InputProps extends HTMLInputProps{
  className?: string;
  value?: string;
  onChange?: (val: string) => void;
}

export const Input = memo(({
  className, value, onChange, type = "text", ...otherProps
}: InputProps) => {
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    onChange?.(e.target.value);
  };

  return (
    <div className={classes(cls.Input, {}, [className])}>
      <input type={type} value={value} onChange={onChangeHandler} />
    </div>
  );
});

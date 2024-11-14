import { classes } from "shared/lib/classNames/classes";
import { InputHTMLAttributes, memo } from "react";
import cls from "./Input.module.scss";

type HTMLInputProps = Omit<InputHTMLAttributes<HTMLInputElement>, "value" | "onChange">

interface IInputProps extends HTMLInputProps{
  className?: string;
  value?: string;
  onChange?: (val: string) => void;
}

export const Input = memo(({
  className,
  value,
  onChange,
  type = "text",
  placeholder,
  ...otherProps
}: IInputProps) => {
  const onChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    onChange?.(e.target.value);
  };

  return (
    <div className={classes(cls.Input, {}, [className])}>
      {placeholder && (
        <div className={cls.placeholder}>
          {placeholder}
        </div>
      )}
      <input
        type={type}
        value={value}
        onChange={onChangeHandler}
        className={cls.inputEntity}
        {...otherProps}
      />
    </div>
  );
});

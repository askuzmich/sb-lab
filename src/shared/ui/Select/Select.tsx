import { classes } from "shared/lib/classNames/classes";

import { ChangeEvent, memo, useMemo } from "react";
import cls from "./Select.module.scss";

export interface ISelectOptions {
  value: string;
  content: string;
}

interface ISelectProps {
  className?: string;
  title?: string;
  defaultValue?: string;
  optionsList?: ISelectOptions[];

  readonly?: boolean;
  onChange?: (val: string) => void;
}

export const Select = memo(({ className, title, defaultValue, optionsList, readonly, onChange }: ISelectProps) => {
  const onOptionPick = (e: ChangeEvent<HTMLSelectElement>) => {
    onChange?.(e.target.value);
  };

  const list = useMemo(() => {
    return optionsList?.map((optionItem) => (
      <option key={optionItem.value} value={optionItem.value}>
        {optionItem.content}
      </option>
    ));
  }, [optionsList]);

  return (
    <div className={classes(cls.Select, {}, [className])}>
      {title && <div className={cls.title}>{title}</div>}
      <select
        disabled={readonly}
        value={defaultValue}
        onChange={onOptionPick}
        className={classes(cls.selectEntity, { [cls.readOnly]: readonly }, [])}
      >
        {list}
      </select>
    </div>
  );
});

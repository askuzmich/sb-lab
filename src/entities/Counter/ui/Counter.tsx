import { useDispatch, useSelector } from "react-redux";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { counterActions } from "entities/Counter/model/slices/counter.slice";
import { useTranslation } from "react-i18next";

import { getCounterValue } from "../model/selectors/getCounterValue/getCounterValue";

export const Counter = () => {
  const { t } = useTranslation("temp");
  const count = useSelector(getCounterValue);

  const dispatch = useDispatch();

  const incHandler = () => {
    dispatch(counterActions.increment());
  };

  const decHandler = () => {
    dispatch(counterActions.decrement());
  };

  return (
    <div data-testid="counter">
      <h3>{t("Счётчик")}</h3>
      <h4 data-testid="counter-value">{count}</h4>
      <div style={{ display: "flex", justifyContent: "center" }}>
        <Button
          data-testid="counter-inc-button"
          theme={ButtonTheme.GREEN}
          onClick={incHandler}
        >
          {t("Добавить")}
        </Button>
        <Button
          data-testid="counter-dec-button"
          theme={ButtonTheme.RED}
          onClick={decHandler}
        >
          {t("Уменьшить")}
        </Button>
      </div>

    </div>
  );
};

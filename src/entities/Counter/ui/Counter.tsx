import { useDispatch, useSelector } from "react-redux";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { counterActions } from "entities/Counter/model/slices/counterSlice";
import { useTranslation } from "react-i18next";

import LikeSVG from "shared/assets/icons/like.svg";
import DislikeSVG from "shared/assets/icons/dislike.svg";

import { memo } from "react";
import { getCounterValue } from "../model/selectors/getCounterValue/getCounterValue";

export const Counter = memo(() => {
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
    <div data-testid="counter" style={{ display: "block", margin: "0 auto", width: 75, position: "relative" }}>
      <div style={{ display: "flex", justifyContent: "space-around" }}>
        <h4 data-testid="counter-value" style={{ color: "#777" }}>{count}</h4>
        <Button
          data-testid="counter-inc-button"
          theme={ButtonTheme.CLEAR}
          onClick={incHandler}
        >
          <LikeSVG width={20} fill="#555" />
        </Button>
        <Button
          data-testid="counter-dec-button"
          theme={ButtonTheme.CLEAR}
          onClick={decHandler}
        >
          <DislikeSVG width={20} fill="#555" />
        </Button>
      </div>

    </div>
  );
});

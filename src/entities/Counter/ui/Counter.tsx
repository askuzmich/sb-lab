import { useSelector } from "react-redux";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { counterActions } from "entities/Counter/model/slices/counterSlice";

import LikeSVG from "shared/assets/icons/like.svg";
import DislikeSVG from "shared/assets/icons/dislike.svg";

import { memo, useCallback } from "react";
import { useAppDispatch } from "shared/lib/hooks/useAppDispatch";
import { getCounterValue } from "../model/selectors/getCounterValue/getCounterValue";

export const Counter = memo(() => {
  const count = useSelector(getCounterValue);

  const dispatch = useAppDispatch();

  const incHandler = useCallback(() => {
    dispatch(counterActions.increment());
  }, [dispatch]);

  const decHandler = useCallback(() => {
    dispatch(counterActions.decrement());
  }, [dispatch]);

  return (
    <div data-testid="counter" style={{ display: "block", margin: "0 auto", width: 75, position: "relative" }}>
      <div style={{ display: "flex", justifyContent: "space-around" }}>
        <h4 data-testid="counter-value" style={{ color: "#777" }}>{count}</h4>
        <Button
          data-testid="counter-inc-button"
          theme={ButtonTheme.CLEAR}
          onClick={incHandler}
        >
          <LikeSVG width={12} fill="#555" />
        </Button>
        <Button
          data-testid="counter-dec-button"
          theme={ButtonTheme.CLEAR}
          onClick={decHandler}
        >
          <DislikeSVG width={12} fill="#555" />
        </Button>
      </div>

    </div>
  );
});

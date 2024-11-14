import { ICounterSchema } from "../types/ICounterSchema";
import { counterActions, counterReducer } from "./counterSlice";

describe("counterSlice.test", () => {
  test("decrement", () => {
    const state:ICounterSchema = { value: 10 };

    expect(counterReducer(state, counterActions.decrement())).toEqual({ value: 9 });
  });

  test("increment", () => {
    const state:ICounterSchema = { value: 10 };

    expect(counterReducer(state, counterActions.increment())).toEqual({ value: 11 });
  });

  test("empty state", () => {
    expect(counterReducer(undefined, counterActions.increment())).toEqual({ value: 1 });
  });
});

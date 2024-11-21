import { DeepPartial } from "@reduxjs/toolkit";
import { IStateSchema } from "app/providers/StoreProvider";
import { getLoginIsLoading } from "./getLoginIsLoading";

describe("getLoginIsLoading.test", () => {
  test("вернёт сост-е () -> true", () => {
    const state: DeepPartial<IStateSchema> = {
      loginForm: {
        isLoading: true
      }
    };

    expect(getLoginIsLoading(state as IStateSchema)).toEqual(true);
  });

  test("с пустым state: () -> false", () => {
    const state: DeepPartial<IStateSchema> = {};

    expect(getLoginIsLoading(state as IStateSchema)).toEqual(false);
  });
});

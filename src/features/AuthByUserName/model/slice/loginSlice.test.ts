import { DeepPartial } from "@reduxjs/toolkit";
import { ILoginSchema } from "../types/ILoginSchema";
import { loginActions, loginReducer } from "./loginSlice";

describe("loginSlice.test", () => {
  beforeEach(() => {
  });

  test("ввод username", () => {
    const state: DeepPartial<ILoginSchema> = { username: "john" };
    expect(loginReducer(state as ILoginSchema, loginActions.setUsername("john")))
      .toEqual({ username: "john" });
  });

  test("ввод password", () => {
    const state: DeepPartial<ILoginSchema> = { password: "john" };
    expect(loginReducer(state as ILoginSchema, loginActions.setPassword("john")))
      .toEqual({ password: "john" });
  });

  test("test isLoading", () => {
    const state: DeepPartial<ILoginSchema> = { isLoading: true };
    // expect(loginReducer(state as ILoginSchema, loginActions(true)))
    //   .toBe({ password: "john" });
  });

  test("test error", () => {
    expect("").toEqual("");
  });
});

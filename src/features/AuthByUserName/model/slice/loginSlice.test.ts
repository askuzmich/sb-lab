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
});

import { ICounterSchema } from "entities/Counter";
import { IUserSchema } from "entities/User";
import { ILoginSchema } from "features/AuthByUserName";

export interface IStateSchema {
  counter: ICounterSchema;
  user: IUserSchema;
  loginForm?: ILoginSchema;
}

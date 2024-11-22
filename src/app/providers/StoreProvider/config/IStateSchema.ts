import { AnyAction, CombinedState, EnhancedStore, Reducer, ReducersMapObject } from "@reduxjs/toolkit";
import { ICounterSchema } from "entities/Counter";
import { IProfileStateSchema } from "entities/Profile";
import { IUserSchema } from "entities/User";
import { ILoginSchema } from "features/AuthByUserName";

export interface IStateSchema {
  counter: ICounterSchema;
  user: IUserSchema;

  // Async
  loginForm?: ILoginSchema;
  profile?: IProfileStateSchema;
}

export type IStateSchemaKey = keyof IStateSchema;

export interface IReducerManager {
  getReducerMap: () => ReducersMapObject<IStateSchema>;

  reduce: (state: IStateSchema, action: AnyAction) => CombinedState<IStateSchema>;

  add: (key: IStateSchemaKey, reducer: Reducer) => void;

  remove: (key: IStateSchemaKey) => void;
}

export interface IStoreManager extends EnhancedStore<IStateSchema> {
  reducerManager: IReducerManager;
}

import { configureStore, ReducersMapObject } from "@reduxjs/toolkit";
import { counterReducer } from "entities/Counter";
import { userReducer } from "entities/User";
import { IStateSchema } from "./IStateSchema";
import { reducerManager } from "./reducerManager";

export function createReduxStore(initialState?: IStateSchema) {
  const rootReducers: ReducersMapObject<IStateSchema> = {
    counter: counterReducer,
    user: userReducer
  };

  const rManager = reducerManager(rootReducers);

  const store = configureStore<IStateSchema>({
    reducer: rManager.reduce,
    preloadedState: initialState,
    devTools: __IS_DEV__
  });

  // @ts-ignore
  store.reducerManager = rManager;

  return store;
}

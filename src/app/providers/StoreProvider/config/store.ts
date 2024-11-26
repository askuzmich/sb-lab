import { configureStore, ReducersMapObject } from "@reduxjs/toolkit";
import { counterReducer } from "entities/Counter";
import { userReducer } from "entities/User";

import { AXIOS } from "resources/restApi/AXIOS";

import { NavigateOptions, To } from "react-router";
import { IStateSchema } from "./IStateSchema";
import { reducerManager } from "./reducerManager";

export function createReduxStore(
  initialState?: IStateSchema,

  asyncReducers?: ReducersMapObject<IStateSchema>,

  navigate?: (to: To, options?: NavigateOptions) => void
) {
  const rootReducers: ReducersMapObject<IStateSchema> = {
    ...asyncReducers,
    counter: counterReducer,
    user: userReducer,
  };

  const rManager = reducerManager(rootReducers);

  const store = configureStore({
    reducer: rManager.reduce,
    preloadedState: initialState,
    devTools: __IS_DEV__,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
      thunk: {
        extraArgument: {
          axios: AXIOS,
          navigate
        },
      },
    }),

  });

  // @ts-ignore
  store.reducerManager = rManager;

  return store;
}

export type TypedDispatch = ReturnType<typeof createReduxStore>["dispatch"];

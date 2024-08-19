import { configureStore } from "@reduxjs/toolkit";
import { counterReducer } from "entities/Counter";
import { IStateSchema } from "./IStateSchema";

export function createReduxStore(initialState?: IStateSchema) {
  return configureStore<IStateSchema>({
    reducer: {
      counter: counterReducer
    },
    preloadedState: initialState,
    devTools: __IS_DEV__
  });
}

// // Infer the `RootState` and `AppDispatch` types from the store itself
// export type RootState = ReturnType<typeof store.getState>
// // Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
// export type AppDispatch = typeof store.dispatch

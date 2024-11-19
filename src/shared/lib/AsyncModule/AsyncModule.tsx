import { Reducer } from "@reduxjs/toolkit";
import { IStoreManager } from "app/providers/StoreProvider";
import { IStateSchemaKey } from "app/providers/StoreProvider/config/IStateSchema";
import { PropsWithChildren, useEffect } from "react";
import { useDispatch, useStore } from "react-redux";

export type ReducerListT = {
  [reducerKey in IStateSchemaKey]?: Reducer
}

type ReducerListNodeType = [IStateSchemaKey, Reducer];

interface AsyncModuleProps {
  // reducerKey: IStateSchemaKey;

  reducers: ReducerListT;

  isRemoveAfterUnmount?: boolean;
}

export const AsyncModule = ({
  children,
  // reducerKey,
  reducers,
  isRemoveAfterUnmount = true
}: PropsWithChildren<AsyncModuleProps>) => {
  const store = useStore() as IStoreManager;

  const dispatch = useDispatch();

  useEffect(() => {
    Object.entries(reducers).forEach(([reducerKey, reducer]: ReducerListNodeType) => {
      store.reducerManager.add(reducerKey, reducer);
      dispatch({ type: `@INIT ${reducerKey} rdcr` });
    });

    return () => {
      if (isRemoveAfterUnmount) {
        Object.keys(reducers).forEach((reducerKey: IStateSchemaKey) => {
          store.reducerManager.remove(reducerKey);
          dispatch({ type: `@REM ${reducerKey} rdcr` });
        });
      }
    };

    // eslint-disable-next-line
  }, []);

  // eslint-disable-next-line react/jsx-no-useless-fragment
  return (<>{ children }</>);
};

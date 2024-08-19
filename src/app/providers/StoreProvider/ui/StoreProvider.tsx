import { ReactNode } from "react";
import { Provider } from "react-redux";
import { createReduxStore } from "../config/store";
import { IStateSchema } from "../config/IStateSchema";

interface StoreProviderProps {
  children?: ReactNode;
  initialState?: IStateSchema;
}

export const StoreProvider = ({ children, initialState }: StoreProviderProps) => {
  const store = createReduxStore(initialState);

  return (<Provider store={store}>{children}</Provider>);
};

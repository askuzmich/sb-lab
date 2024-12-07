import { IStateSchema } from "app/providers/StoreProvider";

export const getUserAuthDataMounted = (state: IStateSchema) => state.user.isAuthDataMounted;

import { IStateSchema } from "app/providers/StoreProvider";

export const getProfile = (state: IStateSchema) => state?.profile?.data;

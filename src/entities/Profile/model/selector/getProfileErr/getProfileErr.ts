import { IStateSchema } from "app/providers/StoreProvider";

export const getProfileErr = (state: IStateSchema) => state?.profile?.error;

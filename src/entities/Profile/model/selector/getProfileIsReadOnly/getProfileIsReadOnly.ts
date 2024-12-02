import { IStateSchema } from "app/providers/StoreProvider";

export const getProfileIsReadOnly = (state: IStateSchema) => state?.profile?.isReadOnly;

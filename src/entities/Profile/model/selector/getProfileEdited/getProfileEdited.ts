import { IStateSchema } from "app/providers/StoreProvider";

export const getProfileEdited = (state: IStateSchema) => state?.profile?.editedData;

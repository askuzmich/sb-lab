import { IStateSchema } from "app/providers/StoreProvider";

export const getProfileValidateErr = (state: IStateSchema) => state?.profile?.validationError;

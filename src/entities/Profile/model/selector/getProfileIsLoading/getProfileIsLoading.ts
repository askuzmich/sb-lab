import { IStateSchema } from "app/providers/StoreProvider";

export const getProfileIsLoading = (state: IStateSchema) => state?.profile?.isLoading;

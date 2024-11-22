import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { IProfileStateSchema } from "../type/IProfile";

const initialState: IProfileStateSchema = {
  readonly: true,
  isLoading: false,
  error: undefined,
  data: undefined,
};

export const profileSlice = createSlice({
  name: "profile",
  initialState,
  reducers: {},
});

export const { actions: profileActions } = profileSlice;
export const { reducer: profileReducer } = profileSlice;

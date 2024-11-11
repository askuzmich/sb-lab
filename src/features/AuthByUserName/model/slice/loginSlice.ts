import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { ILoginSchema } from "../types/ILoginSchema";

const initialState: ILoginSchema = {
  username: "",
  password: "",
  isLoading: false
};

export const loginSlice = createSlice({
  name: "login",
  initialState,
  reducers: {
    setUsername: (state, action: PayloadAction<string>) => {
      state.username = action.payload;
    },
    setPassword: (state, action: PayloadAction<string>) => {
      state.password = action.payload;
    }
  },
});

export const { actions: loginActions } = loginSlice;
export const { reducer: loginReducer } = loginSlice;

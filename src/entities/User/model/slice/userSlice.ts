import { createSlice } from "@reduxjs/toolkit";
import { IUserSchema } from "../types/IUserSchema";

const initialState: IUserSchema = {};

export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
});

export const { actions: userActions } = userSlice;
export const { reducer: userReducer } = userSlice;

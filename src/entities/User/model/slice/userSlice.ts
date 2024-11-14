import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { USER_LOCAL_STORAGE_KEY } from "resources/application";
import { IUser, IUserSchema } from "../types/IUserSchema";

const initialState: IUserSchema = {};

export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    setAuthData: (state, action: PayloadAction<IUser>) => {
      state.authData = action.payload;
    },
    initAuthData: (state, action: PayloadAction<IUser>) => {
      if (localStorage.getItem(USER_LOCAL_STORAGE_KEY) === "undefined") {
        localStorage.removeItem(USER_LOCAL_STORAGE_KEY);
      }

      const user = localStorage.getItem(USER_LOCAL_STORAGE_KEY);

      if (user) {
        state.authData = JSON.parse(user);
      }
    },
    logout: (state) => {
      localStorage.removeItem(USER_LOCAL_STORAGE_KEY);
      state.authData = undefined;
    }
  },
});

export const { actions: userActions } = userSlice;
export const { reducer: userReducer } = userSlice;

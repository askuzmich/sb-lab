import { createAsyncThunk } from "@reduxjs/toolkit";
import {
  API_ENDPOINT_BASE_URL,
  API_ENDPOINT_HOST,
  API_ENDPOINT_HOST_PORT,
  USER_LOCAL_STORAGE_KEY
} from "resources/application";
import axios from "axios";
import { IUser, userActions } from "entities/User";
import { encodeBase64 } from "shared/lib/encode/encode";

interface ILoginByUsernameProps {
  username: string;
  password: string;
}

export const loginByUsername = createAsyncThunk<IUser, ILoginByUsernameProps, { rejectValue: string }>(
  "login/loginByUsername",
  async ({ username, password }, thunkAPI) => {
    try {
      // const Authorization = `Bearer ${token}`;

      const Authorization = `Basic ${encodeBase64(`${username}:${password}`)}`;

      const url = `${API_ENDPOINT_HOST}:${API_ENDPOINT_HOST_PORT}${API_ENDPOINT_BASE_URL}/users/login`;

      const response = await axios.post(
        url,
        {}, // { username, password }, // body
        { headers: { Authorization } } // headers
      );

      if (!response.data) {
        throw new Error();
      }

      localStorage.setItem(USER_LOCAL_STORAGE_KEY, JSON.stringify(response.data.data));
      thunkAPI.dispatch(userActions.setAuthData(response.data.data));

      return response.data.data;
    } catch (e) {
      console.log(e);
      return thunkAPI.rejectWithValue("error");
    }
  }
);

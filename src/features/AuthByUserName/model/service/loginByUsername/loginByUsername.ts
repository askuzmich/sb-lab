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

axios.interceptors.response.use(
  (response) => response,
  (error) => {
    if (!error.response) {
      return Promise.resolve({ data: { isSuccess: false, message: "Please check your server/internet connection" } });
    }

    return Promise.reject(error);
  }
);

export const basicAuth = async (username: string, password: string, postfix: string) => {
  const Authorization = `Basic ${encodeBase64(`${username}:${password}`)}`;

  const url = `${API_ENDPOINT_HOST}:${API_ENDPOINT_HOST_PORT}${API_ENDPOINT_BASE_URL}${postfix}`;

  return axios
    .post(url, {}, { headers: { Authorization } })
    .catch((error) => {
      if (error.response.data.message) {
        return error.response;
      }

      return { data: { isSuccess: false, statusCode: error.response.status, message: "Server error" } };
    });
};

export const loginByUsername = createAsyncThunk<IUser, ILoginByUsernameProps, { rejectValue: string }>(
  "login/loginByUsername",
  async ({ username, password }, thunkAPI) => {
    const response = await basicAuth(username, password, "/users/login");

    if (!response.data) {
      return thunkAPI.rejectWithValue("ошибка");
    }

    if (response.data.isSuccess === false) {
      console.log(response.data);

      return thunkAPI.rejectWithValue(`${response.data.message}`);
    }

    localStorage.setItem(USER_LOCAL_STORAGE_KEY, JSON.stringify(response.data.data));
    thunkAPI.dispatch(userActions.setAuthData(response.data.data));

    return response.data.data;
  }
);

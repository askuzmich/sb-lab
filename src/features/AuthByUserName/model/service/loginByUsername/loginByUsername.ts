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

const basicAuth = async (username: string, password: string, postfix: string, body: any = {}) => {
  const Authorization = `Basic ${encodeBase64(`${username}:${password}`)}`;
  // const Authorization = `Bearer ${token}`;
  const url = `${API_ENDPOINT_HOST}:${API_ENDPOINT_HOST_PORT}${API_ENDPOINT_BASE_URL}${postfix}`;

  const a = axios.create();

  a.interceptors.response.use(
    (response) => response,
    (error) => {
      if (!error.response) {
        return Promise.resolve({ data: { isSuccess: false, message: "Please check your server/internet connection" } });
      }

      return Promise.reject(error);
    }
  );

  return a
    .post(
      url,
      body,
      { headers: { Authorization } }
    )
    .catch((error) => {
      if (error.response.data.message) {
        // The request was made and the server responded with a status code that falls out of the range of 2xx
        console.log(error.response.data);
        // console.log(error.response.status, error.response.headers);
        return error.response;
      }

      // if (error.request) { // The request was made but no response was received
      //   console.log(error.request);
      // }

      // Something happened in setting up the request that triggered an Error
      // console.log(error.config, error.message);

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

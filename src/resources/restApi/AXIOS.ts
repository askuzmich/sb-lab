import axios from "axios";
import {
  API_ENDPOINT_BASE_URL,
  USER_LOCAL_STORAGE_KEY
} from "resources/application";
import { encodeBase64 } from "shared/lib/encode/encode";

// const url = __IS_DEV__
//   ? `${API_ENDPOINT_HOST}:${API_ENDPOINT_HOST_PORT}`
//   : "https://heroku.app";

const baseURL = `${__REST_API__BASE_URL__}${API_ENDPOINT_BASE_URL}`;

export const AXIOS = axios.create({
  baseURL,
  data: {}
});

AXIOS.interceptors.response.use(
  (response) => response,
  (error) => {
    if (!error.response) {
      return Promise.resolve({ data: { isSuccess: false, message: "Please check your server/internet connection" } });
    }

    return Promise.reject(error);
  }
);

export const auth = async (Authorization: string) => {
  return AXIOS
    .post("/users/login", {}, { headers: { Authorization } })
    .catch((error) => {
      if (error.response.data.message) {
        return error.response;
      }

      return { data: { isSuccess: false, statusCode: error.response.status, message: "Server error" } };
    });
};

export const basicAuth = async (username: string, password: string) => {
  const Authorization = `Basic ${encodeBase64(`${username}:${password}`)}`;

  return auth(Authorization);
};

const getToken = () => {
  const userData = `${localStorage.getItem(USER_LOCAL_STORAGE_KEY)}`;

  if (userData === "undefined") return undefined;

  return `Bearer ${JSON.parse(userData).token}`;
};

export const bearerTokenAuth = async () => {
  const Authorization = getToken();

  return auth(Authorization);
};

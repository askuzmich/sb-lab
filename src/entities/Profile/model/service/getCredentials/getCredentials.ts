import { USER_LOCAL_STORAGE_KEY } from "resources/application";

export const getCredentials = () => {
  const userData = localStorage.getItem(USER_LOCAL_STORAGE_KEY);

  if (userData) {
    const user = JSON.parse(userData);
    return user.token;
  }

  return false;
};

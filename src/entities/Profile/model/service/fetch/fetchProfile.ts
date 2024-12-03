import { createAsyncThunk } from "@reduxjs/toolkit";
import { IThunkConf } from "app/providers/StoreProvider";
import { IProfile } from "../../type/IProfile";
import { profileActions } from "../../slice/profileSlice";
import { getCredentials } from "../getCredentials/getCredentials";

interface IFetchProfileProps {
  profileId: number;
}

interface ICustomReturnedData {
  isSuccess: boolean;
  message: string;
  data: IProfile;
}

export const fetchProfile = createAsyncThunk<
  IProfile, IFetchProfileProps, IThunkConf<string>
>(
  "profile/fetchProfile",
  async ({ profileId }, thunkAPI) => {
    const { extra, dispatch, rejectWithValue, } = thunkAPI;

    const response = await extra.axios.get<ICustomReturnedData>(
      `/profiles/${profileId}`,
      { headers: { Authorization: `Bearer ${getCredentials()}` } }
    );

    if (!response.data) {
      return rejectWithValue("ошибка");
    }

    if (response.data.isSuccess === false) {
      console.log(response.data);

      return rejectWithValue(`${response.data.message}`);
    }

    // localStorage.setItem(USER_LOCAL_STORAGE_KEY, JSON.stringify(response.data.data));

    // dispatch(profileActions.setProfileData(response.data.data));

    // extra.navigate("/");

    return response.data.data;
  }
);

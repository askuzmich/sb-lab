import { createAsyncThunk } from "@reduxjs/toolkit";
import { USER_LOCAL_STORAGE_KEY } from "resources/application";
import { IThunkConf } from "app/providers/StoreProvider";
import { EnumValidateProfileErrs, IProfile } from "../../type/IProfile";
import { getProfileEdited } from "../../selector/getProfileEdited/getProfileEdited";
import { validate } from "../validate/validate";

interface IFetchProfileProps {
  profileId: number;
}

interface ICustomReturnedData {
  isSuccess: boolean;
  message: string;
  data: IProfile;
}

export const updateProfile = createAsyncThunk<
  IProfile, IFetchProfileProps, IThunkConf<EnumValidateProfileErrs[]>
>(
  "profile/updateProfile",
  async ({ profileId }, thunkAPI) => {
    const { extra, dispatch, rejectWithValue, getState } = thunkAPI;

    const getCredentials = () => {
      const userData = localStorage.getItem(USER_LOCAL_STORAGE_KEY);

      if (userData) {
        const user = JSON.parse(userData);
        return user.token;
      }

      return false;
    };

    const editedData = getProfileEdited(getState());

    const errs = validate(editedData);

    if (errs.length) {
      return rejectWithValue(errs);
    }

    const response = await extra.axios.put<ICustomReturnedData>(
      `/profiles/${profileId}`,
      editedData,
      { headers: { Authorization: `Bearer ${getCredentials()}` } }
    );

    if (!response.data) {
      return rejectWithValue([EnumValidateProfileErrs.NO_DATA]);
    }

    if (response.data.isSuccess === false) {
      console.log(response.data);

      return rejectWithValue([EnumValidateProfileErrs.SERVER_ERROR]); // `${response.data.message}`;
    }

    // localStorage.setItem(USER_LOCAL_STORAGE_KEY, JSON.stringify(response.data.data));

    // dispatch(profileActions.setProfileData(response.data.data));

    // extra.navigate("/");

    return response.data.data;
  }
);

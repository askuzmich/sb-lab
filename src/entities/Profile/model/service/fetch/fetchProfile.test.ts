import axios, { AxiosStatic } from "axios";
import { Dispatch } from "@reduxjs/toolkit";
import { IStateSchema } from "app/providers/StoreProvider";

import { ECountry } from "entities/Country";
import { ECurrency } from "entities/Currency";
import { fetchProfile } from "./fetchProfile";

jest.mock("axios");

const mockedAxios = jest.mocked(axios, true);

const data = {
  isSuccess: true,
  message: "success",
  data: {
    firstname: "John",
    lastname: "Dore",
    age: 22,
    country: ECountry.Armenia,
    city: "Erevan",
    address: "Mira, 1-23",
    currency: ECurrency.USD,
    image: "jhkjhkjh"
  }
};

describe("fetchProfile.test", () => {
  let dispatch: Dispatch;

  let getState: () => IStateSchema;

  let api: jest.MockedFunctionDeep<AxiosStatic>;

  beforeEach(() => {
    dispatch = jest.fn();
    getState = jest.fn();
    api = mockedAxios;
  });

  test("success", async () => {
    // When...

    mockedAxios.get.mockReturnValue(Promise.resolve({ data }));

    const asyncThunkAction = fetchProfile({ profileId: 1 });

    const resultData = await asyncThunkAction(dispatch, getState, { axios: api });

    // Then
    expect(mockedAxios.get).toHaveBeenCalled();
    expect(resultData.meta.requestStatus).toBe("fulfilled");
    expect(resultData.payload).toEqual(data.data);
  });

  test("failed with 403", async () => {
    // When...

    mockedAxios.get.mockReturnValue(
      Promise.resolve({
        status: 403
      })
    );

    const asyncThunkAction = fetchProfile({ profileId: 1 });

    const resultData = await asyncThunkAction(dispatch, getState, { axios: api });

    // Then
    expect(mockedAxios.get).toHaveBeenCalled();
    expect(resultData.meta.requestStatus).toBe("rejected");
  });
});

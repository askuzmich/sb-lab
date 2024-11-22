import { ECountry, ECurrency } from "shared/enums/profile";

export interface IProfile {
  owner: number;
  firstname: string;
  lastname: string;
  age: number;
  country: ECountry;
  city: string;
  address: string;
  image: string;
  currency: ECurrency;
}

export interface IProfileStateSchema {
  data?: IProfile;
  isLoading: boolean;
  error?: string;

  readonly: boolean;
}

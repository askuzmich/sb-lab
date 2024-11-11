export interface IUser {
  id: number;
  name: string;
  roles: string;
  enabled: boolean;
}

export interface IUserSchema {
  authData?: IUser;
}

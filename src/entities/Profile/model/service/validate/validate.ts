import { EnumValidateProfileErrs, IProfile } from "../../type/IProfile";

export const validate = (profile?: IProfile) => {
  if (!profile) {
    return [EnumValidateProfileErrs.NO_DATA];
  }
  const { firstname, lastname, age, country } = profile;

  const errs: EnumValidateProfileErrs[] = [];

  if (!firstname || !lastname) {
    errs.push(EnumValidateProfileErrs.INCORRECT_USER_DATA);
  }

  if (!age || !Number.isInteger(age)) {
    errs.push(EnumValidateProfileErrs.INCORRECT_AGE);
  }

  if (!country) {
    errs.push(EnumValidateProfileErrs.INCORRECT_COUNTRY);
  }

  return errs;
};

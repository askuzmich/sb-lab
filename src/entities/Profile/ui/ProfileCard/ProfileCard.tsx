import { classes } from "shared/lib/classNames/classes";
import { useTranslation } from "react-i18next";
import { useSelector } from "react-redux";
import { getProfile } from "entities/Profile/model/selector/getProfile/getProfile";
import { getProfileErr } from "entities/Profile/model/selector/getProfileErr/getProfileErr";
import { getProfileIsLoading } from "entities/Profile/model/selector/getProfileIsLoading/getProfileIsLoading";
import { Text } from "shared/ui/Text/Text";
import { Button, ButtonTheme } from "shared/ui/Button/Button";
import { Input } from "shared/ui/Input/Input";
import cls from "./ProfileCard.module.scss";

interface ProfileCardProps {
  className?: string;
}

export const ProfileCard = ({ className }: ProfileCardProps) => {
  const { t } = useTranslation("profile");

  const data = useSelector(getProfile);

  const isLoading = useSelector(getProfileIsLoading);

  const error = useSelector(getProfileErr);

  return (
    <div className={classes(cls.ProfileCard, {}, [className])}>
      <Text title={t("Карточка профиля")} />
      <div className={cls.data}>
        {/* <Input value={data?.image} placeholder={t("имя")} className={cls.input} /> */}

        <Input value={data?.firstname} placeholder={t("Имя")} className={cls.input} />
        <Input value={data?.lastname} placeholder={t("Фамилия")} className={cls.input} />
        <Input value={String(data?.age || "")} placeholder={t("Лет")} className={cls.input} />
        <Input value={data?.country} placeholder={t("Страна")} className={cls.input} />
        <Input value={data?.city} placeholder={t("Город")} className={cls.input} />
        <Input value={data?.address} placeholder={t("Адрес")} className={cls.input} />
        <Input value={data?.currency} placeholder={t("Валюта")} className={cls.input} />

        <Button theme={ButtonTheme.GREEN}>{t("Редактировать")}</Button>
      </div>
    </div>
  );
};

import { Counter } from "entities/Counter";
import React from "react";
import { useTranslation } from "react-i18next";

const AboutPage = () => {
  const { t } = useTranslation("about");

  return (
    <div>
      <h2 className="App-link">{t("о проекте")}</h2>

      <Counter />
    </div>
  );
};

export default AboutPage;

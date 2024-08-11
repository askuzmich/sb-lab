import { render } from "@testing-library/react";
import { ReactNode } from "react";
import { I18nextProvider } from "react-i18next";
import { MemoryRouter } from "react-router-dom";
import i18nForTests from "shared/config/i18n/i18nForTests";

export interface IRenderTest {
  route?: string;
}

export const RenderTest = (Component: ReactNode, options: IRenderTest = {}) => {
  const { route = "/" } = options;

  return render(
    <MemoryRouter initialEntries={[route]}>
      <I18nextProvider i18n={i18nForTests}>
        {Component}
      </I18nextProvider>
    </MemoryRouter>
  );
};

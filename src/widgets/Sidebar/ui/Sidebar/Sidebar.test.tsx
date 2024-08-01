import { fireEvent, render, screen } from "@testing-library/react";
import { RenderI18n } from "shared/lib/test/RenderI18n/RenderI18n";
import { Sidebar } from "./Sidebar";

describe("Проверка компонента <Sidebar />", () => {
  test("тест на присутствие компонента <Sidebar />", () => {
    RenderI18n(<Sidebar />);
    expect(screen.getByTestId("sidebar")).toBeInTheDocument();
  });

  test("тест на сворачивание  <Sidebar />", () => {
    RenderI18n(<Sidebar />);
    expect(screen.getByTestId("sidebar")).toHaveClass("collapsed");
    const btn = screen.getByTestId("toggle-sidebar-btn");
    fireEvent.click(btn);
    expect(screen.getByTestId("sidebar")).not.toHaveClass("collapsed");
    // screen.debug();
  });
});

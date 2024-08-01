import { render, screen } from "@testing-library/react";
import { Button, ButtonTheme } from "./Button";

describe("Проверка компонента-кнопки", () => {
  test("тест на присутствие компонента", () => {
    render(<Button>*</Button>);
    expect(screen.getByText("*")).toBeInTheDocument();
  });

  test("тест на присутствие класса \"red\" в кнопке", () => {
    render(<Button theme={ButtonTheme.RED}>*</Button>);
    expect(screen.getByText("*")).toHaveClass("red");
    // screen.debug();
  });
});

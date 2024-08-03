import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";

// import { ThemeDecorator } from "shared/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "app/providers/ThemeProvider";
import { ThemeDecorator } from "shared/config/storybook/ThemeDecorator/ThemeDecorator";
import { Button, ButtonTheme } from "./Button";

export default {
  title: "shared/Button",
  component: Button,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof Button>;

const Template: ComponentStory<typeof Button> = (args) => <Button {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  children: "Text",
};

export const Clear = Template.bind({});
Clear.args = {
  children: "Text",
  theme: ButtonTheme.CLEAR,
};

export const Gray = Template.bind({});
Gray.args = {
  children: "Text",
  theme: ButtonTheme.GRAY,
};

export const Green = Template.bind({});
Green.args = {
  children: "Text",
  theme: ButtonTheme.GREEN,
};
// Green.decorators = [ThemeDecorator(Theme.DARK)];

import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";

import { Theme } from "app/providers/ThemeProvider";
import { ThemeDecorator } from "shared/config/storybook/ThemeDecorator/ThemeDecorator";
import { Navbar } from "./Navbar";

export default {
  title: "widget/Navbar",
  component: Navbar,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof Navbar>;

const Template: ComponentStory<typeof Navbar> = (args) => <Navbar {...args} />;

export const Primary = Template.bind({});
Primary.args = {};

export const SecondaryDark = Template.bind({});
SecondaryDark.args = {};
SecondaryDark.decorators = [ThemeDecorator(Theme.DARK)];

import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
import { Country } from "./Country";

export default {
  title: "entities/Country",
  component: Country,
  argTypes: {
    backgroundColor: { control: "color" },
  }
} as ComponentMeta<typeof Country>;

const Template: ComponentStory<typeof Country> = (args) => <Country {...args} />;

export const Primary = Template.bind({});
Primary.args = {};

import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
import { Currency } from "./Currency";

export default {
  title: "entities/Currency",
  component: Currency,
  argTypes: {
    backgroundColor: { control: "color" },
  }
} as ComponentMeta<typeof Currency>;

const Template: ComponentStory<typeof Currency> = (args) => <Currency {...args} />;

export const Primary = Template.bind({});
Primary.args = {};

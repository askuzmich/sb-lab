import React, { Suspense } from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";

// import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { Theme } from "app/providers/ThemeProvider";
import { ThemeDecorator } from "resources/config/storybook/ThemeDecorator/ThemeDecorator";
import { StoreProvider } from "app/providers/StoreProvider";
import ProfilePage from "./ProfilePage";

export default {
  title: "pages/ProfilePage",
  component: ProfilePage,
  argTypes: {
    backgroundColor: { control: "color" },
  },
  decorators: [
    (Story) => (
      <StoreProvider>
        <Story />
      </StoreProvider>
    ),
  ],
} as ComponentMeta<typeof ProfilePage>;

const Template: ComponentStory<typeof ProfilePage> = (args: any) => <ProfilePage {...args} />;

export const Light = Template.bind({});
Light.args = {};

export const Dark = Template.bind({});
Dark.args = {};
Dark.decorators = [ThemeDecorator(Theme.DARK)];

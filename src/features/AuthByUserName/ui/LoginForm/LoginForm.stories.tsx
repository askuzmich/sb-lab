import React from "react";
import { ComponentStory, ComponentMeta } from "@storybook/react";
import { StoreDecorator } from "resources/config/storybook/StoreDecorator/StoreDecorator";
import LoginForm from "./LoginForm";

export default {
  title: "feature/LoginForm",
  component: LoginForm,
  argTypes: {
    backgroundColor: { control: "color" },
  },
} as ComponentMeta<typeof LoginForm>;

const Template: ComponentStory<typeof LoginForm> = (args) => <LoginForm {...args} />;

export const Primary = Template.bind({});
Primary.args = {};
Primary.decorators = [StoreDecorator({
  loginForm: { username: "John", password: "Alexander" }
})];

export const LoginFormWithError = Template.bind({});
LoginFormWithError.args = {};
LoginFormWithError.decorators = [StoreDecorator({
  loginForm: { username: "NotExistUserName", password: "WrOnGPaSs", error: "Server error" }
})];

export const LoginFormWithLoadingState = Template.bind({});
LoginFormWithLoadingState.args = {};
LoginFormWithLoadingState.decorators = [StoreDecorator({
  loginForm: { username: "UserName", password: "Pass", isLoading: true }
})];

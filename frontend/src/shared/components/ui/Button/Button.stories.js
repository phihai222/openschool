import Button from "@shared/components/ui/Button/index.js";
import {fn} from "storybook/test";
import {defineComponentPath} from "@shared/utils/storybook/defineComponentPath.js";



export default {
  title: 'Shared/UI/Button',
  component: Button,
  parameters: defineComponentPath('@shared/components/ui/Button/index.js'),
  tags: ['autodocs'],
  args: {
    label: 'Click me!',
    onClick: fn()
  },
  argTypes: {
    backgroundColor: {control: 'color'},
  },
};


export const Primary = {
  args: {
    label: "primary",
    variant: 'primary',
  },
};

export const Secondary = {
  args: {
    label: "primary",
    variant: 'secondary',
  },
};

export const Large = {
  args: {
    size: 'large',
    label: 'Button',
  },
};

export const Small = {
  args: {
    size: 'small',
    label: 'Button',
  },
};

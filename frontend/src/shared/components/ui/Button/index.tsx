import { FC } from "react";
import styles from "./button.module.scss";

type ButtonProps = {
  label: string;
  onClick?: () => void;
  variant?: "primary" | "secondary";
  disabled?: boolean;
  type?: "button" | "submit";
};

const Button: FC<ButtonProps> = ({
  label,
  onClick,
  variant = "primary",
  disabled,
  type,
}) => {
  const baseClass = styles.btn;
  const variantClass = styles[`btn-${variant}`] ?? "";
  const className = `${baseClass} ${variantClass}`;

  return (
    <button
      className={className}
      onClick={onClick}
      disabled={disabled}
      type={type}
    >
      {label}
    </button>
  );
};

export default Button;

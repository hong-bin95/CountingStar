import React from "react";
import { ToggleButtonType } from "../../types/SpotType";

const ToggleButton: React.FC<ToggleButtonType> = ({
  onClick,
  isMainVisible,
}) => {
  const buttonText = isMainVisible
    ? "지도에서 보기"
    : "오늘은 어디에 별이 뜰까요?";

  return (
    <button
      onClick={onClick}
      className="toggle-button fixed top-4 right-6 z-50
      w-26
      bg-transparent hover:bg-sky-700 text-white font-semibold hover:text-white py-2 px-4 border border-sky-300 hover:border-transparent rounded"
    >
      {buttonText}
    </button>
  );
};

export default ToggleButton;

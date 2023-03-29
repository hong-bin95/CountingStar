import React from "react";
import { ToggleButtonType } from "../../types/SpotType";

const ToggleButton: React.FC<ToggleButtonType> = ({ onClick }) => {
  return (
    <button
      onClick={onClick}
      className="toggle-button fixed top-4 right-6 z-50
      bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded"
    >
      {/* 토글 버튼 아이콘 또는 텍스트를 여기에 추가 */}
      Toggle
    </button>
  );
};

export default ToggleButton;

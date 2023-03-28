import React from "react";

interface ToggleButtonProps {
  onClick: () => void;
}

const ToggleButton: React.FC<ToggleButtonProps> = ({ onClick }) => {
  return (
    <button
      onClick={onClick}
      className="toggle-button fixed top-4 right-4 bg-white p-3 rounded-full shadow-md z-50"
    >
      {/* 토글 버튼 아이콘 또는 텍스트를 여기에 추가 */}
      Toggle
    </button>
  );
};

export default ToggleButton;

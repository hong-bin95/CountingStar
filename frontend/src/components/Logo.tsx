import React from "react";
import logo from "../assets/CountingStar.png";
import { useNavigate } from "react-router-dom";
type Props = {};

function Logo({}: Props) {
  const navigate = useNavigate();
  function moveToMain() {
    navigate("/");
  }

  return (
    <>
      <img
        src={logo}
        alt="countingStar logo"
        className="w-20 rounded-3xl"
        onClick={moveToMain}
      />
    </>
  );
}

export default Logo;

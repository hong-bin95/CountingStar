import React from "react";
import { useSelector, useDispatch } from 'react-redux';
import {updateDay, DetailsData} from '../store/DetailsSlice';
import logo from "../assets/CountingStar.png";
import { useNavigate } from "react-router-dom";
type Props = {};

function Logo({}: Props) {

  const dispatch = useDispatch();
  const navigate = useNavigate();
  function moveToMain() {
    navigate("/");
    dispatch(updateDay(0));
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

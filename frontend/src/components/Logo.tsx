import React from "react";
import { useSelector, useDispatch } from 'react-redux';
import {update, DetailsData} from '../store/DetailsSlice';
import logo from "../assets/CountingStar.png";
import { useNavigate } from "react-router-dom";
type Props = {};

function Logo({}: Props) {

  const dispatch = useDispatch();
  const day = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.day);
    
  const navigate = useNavigate();
  function moveToMain() {
    navigate("/");
    dispatch(update(0));
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

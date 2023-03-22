import React from "react";
import nightSky from "../../assets/nightSkyExample.jpg";

type Props = {};

function ThisMonthBox({}: Props) {
  return (
    <>
      <div className="border border-gray-300  rounded-2xl shadow-md w-full">
        <div className="text-white bg-blue-100">큰 곰 자리</div>
        <img src={nightSky} className="w-full rounded-2xl" />
        <div></div>
      </div>
    </>
  );
}

export default ThisMonthBox;

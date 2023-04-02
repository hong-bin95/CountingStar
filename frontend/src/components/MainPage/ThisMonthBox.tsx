import React from "react";
import nightSky from "../../assets/nightSkyExample.jpg";

type Props = {
  stellaName: string;
  cnt: number;
};

function ThisMonthBox(props: Props) {
  // console.log(props.stellaName);
  // console.log(props.cnt);
  return (
    <>
      <div className="relative border border-gray-300  rounded-2xl shadow-md w-full">
        <div className="absolute text-white text-2xl ml-10 mt-3 font-serif tracking-wider">
          {props.stellaName}
        </div>
        <img src={nightSky} className="w-full rounded-2xl" />
        <div></div>
      </div>
    </>
  );
}

export default ThisMonthBox;

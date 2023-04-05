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
      <div className="relative border border-gray-300 rounded-2xl shadow-md w-full transition delay-100 hover:scale-110  duration-100">
        <div className="absolute text-white text-xl ml-10 mt-3 font-serif tracking-wider transition delay-300 hover:scale-110 duration-100">
          {props.stellaName}
        </div>
        <img
          src={`https://counting-star.com/images/star/${props.stellaName}.PNG`}
          className="w-full rounded-2xl h-60"
        />
        <div></div>
      </div>
    </>
  );
}

export default ThisMonthBox;

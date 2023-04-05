import React from "react";
import { MonthProps } from "../../types/mainType";

function ThisMonthBox(props: MonthProps) {
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

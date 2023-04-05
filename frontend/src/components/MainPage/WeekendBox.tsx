import React from "react";
import StarPoint from "./StarPoint";
import { weekendBoxProps } from "../../types/mainType";

function WeekendBox(props: weekendBoxProps) {
  return (
    <>
      <div className="border border-gray-200 rounded-3xl text-2xl shadow-md w-full bg-blue-50 transition delay-100 hover:scale-110 hover:bg-red-50 duration-100">
        <div className="text-center pt-7 font-serif">{props.spotName}</div>
        <div>
          <StarPoint grade={props.grade} />
        </div>
      </div>
    </>
  );
}

export default WeekendBox;

import React from "react";
import StarPoint from "./StarPoint";

type Props = {
  spotName: string;
  grade: number;
};

function WeekendBox(props: Props) {
  return (
    <>
      <div className="border border-gray-200 rounded-3xl text-2xl shadow-md w-full transition delay-100 hover:scale-110 hover:bg-red-100 duration-100">
        <div className="text-center pt-7 font-serif">{props.spotName}</div>
        <div>
          <StarPoint grade={props.grade} />
        </div>
      </div>
    </>
  );
}

export default WeekendBox;

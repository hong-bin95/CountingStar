import React from "react";
import WeekendBox from "./WeekendBox";

type Props = {};

function WeekendMain({}: Props) {
  return (
    <>
      <div className="text-4xl py-6 text-center font-serif">
        이번 주말 별자리 명소
      </div>
      <div className="grid grid-cols-12 gap-10 mx-auto my-1 ">
        <div className="col-span-4">
          <WeekendBox />
        </div>
        <div className="col-span-4">
          <WeekendBox />
        </div>
        <div className="col-span-4">
          <WeekendBox />
        </div>
      </div>
    </>
  );
}

export default WeekendMain;

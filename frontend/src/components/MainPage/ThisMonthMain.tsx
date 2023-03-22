import React from "react";
import ThisMonthBox from "./ThisMonthBox";

type Props = {};

function ThisMonthMain({}: Props) {
  return (
    <>
      <div className="text-4xl py-6 text-center">이번 달의 별자리</div>
      <div className="grid grid-cols-12 gap-10 mx-auto my-1 ">
        <div className="col-span-4">
          <ThisMonthBox />
        </div>
        <div className="col-span-4">
          <ThisMonthBox />
        </div>
        <div className="col-span-4">
          <ThisMonthBox />
        </div>
      </div>
    </>
  );
}

export default ThisMonthMain;

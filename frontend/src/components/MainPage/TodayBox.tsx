import React from "react";
import StarPoint from "./StarPoint";

type Props = {};

function TodayBox({}: Props) {
  return (
    <>
      <div className="border border-gray-200 rounded-3xl shadow-md w-full">
        <div className="text-center pt-7">서울시 강남구</div>
        <div>
          <StarPoint />
        </div>
      </div>
    </>
  );
}

export default TodayBox;

import React from "react";
import StarPoint from "./StarPoint";

type Props = {
  spotName: string;
  grade: number;
};

function SearchBox(props: Props) {
  return (
    <>
      <div className="border border-gray-200 rounded-3xl shadow-md w-full">
        <div className="text-center pt-7">{props.spotName}</div>
        <div>
          <StarPoint grade={props.grade} />
        </div>
      </div>
    </>
  );
}

export default SearchBox;

import React from "react";
import { useState } from "react";
import starScore from "../../assets/fiveStar.png";

type Props = {
  grade: number;
};

function StarPoint(props: Props) {
  const [score, setScore] = useState(props.grade);

  function repeatStar(score: number): JSX.Element[] {
    let arr = [];
    for (let i = 0; i < score; i++) {
      arr.push(
        <p key={i}>
          <img src={starScore} className="w-6 mx-2" />
        </p>
      );
    }

    return arr;
  }

  return (
    <>
      <div className="flex justify-center pt-20 pb-24">{repeatStar(score)}</div>
    </>
  );
}

export default StarPoint;

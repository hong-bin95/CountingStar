import React from "react";
import { useState } from "react";
import starScore from "../../assets/fiveStar.png";

type Props = {};

function StarPoint({}: Props) {
  const [score, setScore] = useState(5);

  function repeatStar(score: number): JSX.Element[] {
    let arr = [];
    for (let i = 0; i < score; i++) {
      arr.push(
        <p>
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

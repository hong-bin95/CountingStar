import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";
import ThisMonthBox from "./ThisMonthBox";
import arrowLeft from "../../assets/arrowsLeft.png";
import arrowRight from "../../assets/arrowsRight.png";

type Props = {};

interface stella {
  constellationId: number;
  constellationName: string;
}

function ThisMonthMain(props: Props) {
  let now = new Date();

  const [constellaList, setConstellaList] = useState<Array<stella>>([]);
  const [thisMonth, setThisMonth] = useState<number>(now.getMonth() + 1);

  useEffect(() => {
    let year = now.getFullYear().toString();
    let month = thisMonth.toString().padStart(2, "0");

    axios
      .get("https://counting-star.com/api/constellation/rank", {
        params: {
          baseDateYear: "2001",
          baseDateMonth: month,
          limit: 6,
          // baseDateYear: { year },
          // baseDateMonth: { month },
          // limit: 5,
        },
      })
      .then(function (response) {
        console.log(response.data);
        console.log("구분");
        setConstellaList(response.data.data);
        console.log("리스트 저장한거 출력");
        console.log(constellaList);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [thisMonth]);

  const back = () => {
    if (thisMonth === 12) {
      setThisMonth(1);
    } else {
      setThisMonth(thisMonth + 1);
    }
  };

  const forward = () => {
    if (thisMonth === 1) {
      setThisMonth(12);
    } else {
      setThisMonth(thisMonth - 1);
    }
  };

  return (
    <>
      <div className="grid grid-cols-12 gap-10 mx-auto my-1">
        <div
          className="col-span-4 pt-5  grid justify-items-end"
          onClick={forward}
        >
          <img
            src={arrowLeft}
            className="transition delay-100 hover:scale-125 duration-100"
          />
        </div>
        <div className="col-span-4 text-4xl py-8 text-center font-serif">
          {thisMonth}월의 별자리
        </div>
        <div
          className="col-span-4 pt-5 grid justify-items-start "
          onClick={back}
        >
          <img
            src={arrowRight}
            className="transition delay-100 hover:scale-125 duration-100"
          />
        </div>
      </div>

      <div className="grid grid-cols-12 gap-10 mx-auto my-2 pb-7">
        {constellaList.map((star, idx) => (
          <div className="col-span-4" key={idx}>
            <ThisMonthBox
              stellaName={star.constellationName}
              cnt={star.constellationId}
            />
          </div>
        ))}
      </div>
    </>
  );
}

export default ThisMonthMain;

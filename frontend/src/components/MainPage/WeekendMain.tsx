import React, { useEffect, useState } from "react";
import axios from "axios";
import WeekendBox from "./WeekendBox";

type Props = {};

interface spot {
  spotName: string;
  grade: number;
}

function WeekendMain({}: Props) {
  const [spotList, setSpotList] = useState<Array<spot>>([]);

  useEffect(() => {
    axios
      .get("https://counting-star.com/api/spot/ranking", {
        params: {
          baseDateYear: "2023",
          baseDateMonth: "03",
          baseDateDay: "23",
          baseDateHour: "11",
          baseDateMinute: "00",
          limit: 5,
        },
      })
      .then(function (response) {
        console.log(response);
        console.log("구분");

        setSpotList(response.data.data);
        console.log(spotList);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  return (
    <>
      <div className="text-4xl py-6 text-center font-serif">
        이번 주말 별자리 명소
      </div>
      <div className="grid grid-cols-12 gap-10 mx-auto my-1 ">
        {spotList.map((spot, idx) => (
          <div className="col-span-4" key={idx}>
            <WeekendBox spotName={spot.spotName} grade={spot.grade} />
          </div>
        ))}
      </div>
    </>
  );
}

export default WeekendMain;

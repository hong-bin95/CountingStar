import React from "react";
import { useEffect, useState } from "react";
import Logo from "../Logo";
import TodayBox from "../../components/MainPage/TodayBox";
import axios from "axios";

type Props = {};

interface spot {
  spotName: string;
  grade: number;
}

function TodayMain({}: Props) {
  const [spotList, setSpotList] = useState<Array<spot>>([]);

  useEffect(() => {
    let now = new Date();
    let year = now.getFullYear().toString();
    let month = ("0" + (now.getMonth() + 1)).slice(-2);
    let day = now.getDate().toString();

    let hours = now.getHours().toString();

    console.log(year, month, day, hours);
    console.log(typeof year);
    console.log(typeof month);
    console.log(typeof day);
    console.log(typeof hours);

    axios
      .get("https://counting-star.com/api/spot/ranking", {
        params: {
          // baseDateYear: { year },
          // baseDateMonth: { month },
          // baseDateDay: { day },
          // baseDateHour: { hours },
          // baseDateMinute: "00",
          // limit: 5,

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
      <div className="grid grid-cols-12 gap-4 mb-7">
        <div className="col-span-2 ">
          <Logo />
        </div>
        <div className="col-span-8 ">
          <p className="text-4xl text-center py-4 font-serif">
            오늘은 어디에 별이 많이 뜰까요?
          </p>
        </div>
        <div className="col-span-2 ">버튼 컴포넌트</div>
      </div>
      {/* <div className="grid grid-cols-12 gap-10 mx-auto my-1 ">
        {spotList.map((spot, idx) => (
          <div className="col-span-4" key={idx}>
            <TodayBox spotName={spot.spotName} grade={spot.grade} />
          </div>
        ))}
      </div> */}
    </>
  );
}

export default TodayMain;

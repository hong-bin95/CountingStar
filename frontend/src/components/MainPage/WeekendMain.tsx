import React, { useEffect, useState } from "react";
import axios from "axios";
import WeekendBox from "./WeekendBox";
import { useNavigate } from "react-router-dom";
type Props = {};

interface spot {
  spotName: string;
  grade: number;
  spotId: number;
}

function WeekendMain({}: Props) {
  const [spotList, setSpotList] = useState<Array<spot>>([]);
  const [satSun, setSatSun] = useState<string>("토");
  const navigate = useNavigate();

  let now = new Date();
  let year = now.getFullYear().toString();
  let month = ("0" + (now.getMonth() + 1)).slice(-2);

  //getDay() => 일요일부터 0~6
  let weekDay = now.getDay();
  let day = weekDay.toString().padStart(2, "0");

  if (weekDay === 0) {
    //오늘이 일요일일 경우, 다음주 주말 보여주기
    if (satSun === "토") {
      weekDay = now.getDate() + (6 - weekDay);
    } else {
      weekDay = now.getDate() + (7 - weekDay);
    }

    //31일 넘어가면
    if (weekDay > 31) {
      month = ("0" + (now.getMonth() + 2)).slice(-2);
      day = (weekDay - 31).toString().padStart(2, "0");
    }
  } else {
    //보여주는 요일이 토요일일 경우 오늘 요일 + 5, 일요일일 경우 오늘 요일 +6
    if (satSun === "토") {
      weekDay = now.getDate() + (6 - weekDay);
    } else {
      weekDay = now.getDate() + (7 - weekDay);
    }

    //31일 넘어가면
    if (weekDay > 31) {
      month = ("0" + (now.getMonth() + 2)).slice(-2);
      day = (weekDay - 31).toString().padStart(2, "0");
    }
  }

  useEffect(() => {
    axios
      .get("https://counting-star.com/api/spot/ranking", {
        params: {
          baseDateYear: year,
          baseDateMonth: month,
          baseDateDay: day,
          baseDateHour: "00",
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
  }, [day]);

  const changeSunToSat = (e: React.MouseEvent<HTMLButtonElement>) => {
    setSatSun("토");
  };

  const changeSatToSun = (e: React.MouseEvent<HTMLButtonElement>) => {
    setSatSun("일");
  };

  const navigateToDetail = (spotId: number) => {
    navigate(`/detail/${spotId}`);
  };

  return (
    <>
      <div className="text-4xl py-6 text-center font-serif">
        이번 주말 별자리 명소
      </div>
      <div className="ml-4 justify-items-end">
        <button
          className="border border-gray-300 px-3 mb-1 mr-2 h-8 w-24 rounded-xl bg-red-200 font-serif transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-300 duration-100 shadow-md"
          onClick={changeSunToSat}
        >
          토요일
        </button>
        <button
          className="border border-gray-300 px-4 mb-1 h-8 w-24 rounded-xl bg-red-200 font-serif transition ease-in-out delay-150 hover:-translate-y-1 hover:scale-110 hover:bg-red-300 duration-100 shadow-md"
          onClick={changeSatToSun}
        >
          일요일
        </button>
      </div>
      <div className="grid grid-cols-12 gap-10 mx-auto my-4 mb-10 ">
        {spotList &&
          spotList.map((spot, idx) => (
            <div
              className="col-span-4"
              key={idx}
              onClick={() => {
                navigateToDetail(spot.spotId);
              }}
            >
              <WeekendBox spotName={spot.spotName} grade={spot.grade} />
            </div>
          ))}
      </div>
    </>
  );
}

export default WeekendMain;

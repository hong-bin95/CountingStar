import React, { useState } from "react";
import search from "../../assets/search.png";
import SearchBox from "./SearchBox";
import questionMark from "../../assets/question.png";
import styled from "styled-components";
import axios from "axios";
import { useNavigate } from "react-router-dom";

type Props = {};

interface hourToString {
  hourNum: number;
  hourString: string;
}

type spotData = {
  spotId: number;
  spotName: string;
  latitude: number;
  longtitude: number;
  areaCode: number;
};

type result = {
  spot: spotData;
  grade: number;
};

function SearchMain({}: Props) {
  //시간, 날짜, 구군
  const [timeValue, setTimeValue] = useState<string>("00");
  const [dateValue, setDateValue] = useState<string>("날짜 선택");
  const [regionValue, setregionValue] = useState<string>("");
  const [searchResult, setSearchResult] = useState<Array<result>>();
  const [searchRegion, setSearchRegion] = useState<string>();

  //물음표 마우스오버시
  const [hover, setHover] = useState<string>("");
  //자동완성 저장 위한 객체 배열
  const [word, setWord] = useState<Array<result>>();

  const navigate = useNavigate();

  //시간(1) option(01시)로 변경하기
  let hours: hourToString[] = [];
  // let obj: hourToString = { hourNum: 100, hourString: "시간 선택" };
  // hours.push(obj);

  for (let i = 0; i < 24; i++) {
    let timeNum: number = i;
    let timeString: string = ("0" + i).slice(-2) + "시";

    let obj: hourToString = { hourNum: timeNum, hourString: timeString };

    hours.push(obj);
  }

  //날짜 제한 (오늘~10일 후)
  let date = new Date();
  let year = date.getFullYear();
  let month = (date.getMonth() + 1).toString().padStart(2, "0");
  let day = date.getDate().toString().padStart(2, "0");

  //3월 넘어가면
  let lastMonthNum = date.getMonth() + 1;
  console.log(lastMonthNum);
  let lastMonth = lastMonthNum.toString().padStart(2, "0");
  if (date.getDate() + 10 > 31) {
    lastMonth = (lastMonthNum + 1).toString().padStart(2, "0");
  }
  let lastDate = new Date(date.setDate(date.getDate() + 10));

  let lastDay = lastDate.getDate().toString().padStart(2, "0");

  let todayString = year + "-" + month + "-" + day;
  let lastDayString = year + "-" + lastMonth + "-" + lastDay;

  //----시간 select 되면-----//////////////////////////////////////////////////////////////////
  const handleSelectTime = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setTimeValue(e.target.value);

    //날짜 선택되어 있을 때,

    let baseDateY = dateValue.slice(0, 4);
    let baseDateM = dateValue.slice(5, 7);
    let baseDateD = dateValue.slice(8, 10);

    if (dateValue !== "날짜 선택") {
      console.log("시간 select axios");
      axios
        .get("https://counting-star.com/api/grade/", {
          params: {
            baseDateDay: baseDateD,
            baseDateHour: ("0" + timeValue).slice(-2).toString(),
            baseDateMinute: "00",
            baseDateMonth: baseDateM,
            baseDateYear: baseDateY,
            keyword: regionValue,
            limit: 100,
            searchType: "NAME",
          },
        })
        .then(function (response) {
          setWord(response.data.data);
        })
        .catch(function (err) {
          console.log(err);
        });
    }
  };

  //----날짜 select 되면-----//////////////////////////////////////////////////////////////////
  const handleSelectDate = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDateValue(e.target.value);

    let baseDateY = dateValue.slice(0, 4);
    let baseDateM = dateValue.slice(5, 7);
    let baseDateD = dateValue.slice(8, 10);

    console.log("날짜 select axios");
    axios
      .get("https://counting-star.com/api/grade/", {
        params: {
          baseDateDay: baseDateD,
          baseDateHour: ("0" + timeValue).slice(-2).toString(),
          baseDateMinute: "00",
          baseDateMonth: baseDateM,
          baseDateYear: baseDateY,
          keyword: regionValue,
          limit: 100,
          searchType: "NAME",
        },
      })
      .then(function (response) {
        setWord(response.data.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  //----검색 버튼 클릭시-----//////////////////////////////////////////////////////////////////
  const handleSearchButton = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setSearchRegion(regionValue);

    if (dateValue === "날짜 선택") {
      alert("별 보러 갈 날짜를 선택해주세요");
    }

    let baseDateY = dateValue.slice(0, 4);
    let baseDateM = dateValue.slice(5, 7);
    let baseDateD = dateValue.slice(8, 10);

    axios
      .get("https://counting-star.com/api/grade/", {
        params: {
          baseDateDay: baseDateD,
          baseDateHour: ("0" + timeValue).slice(-2).toString(),
          baseDateMinute: "00",
          baseDateMonth: baseDateM,
          baseDateYear: baseDateY,
          keyword: regionValue,
          limit: 100,
          searchType: "NAME",
        },
      })
      .then(function (response) {
        setSearchResult(response.data.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };

  //검색어 입력시 입력값 수정해주는 함수
  const handleSearchInput = (e: React.FormEvent<HTMLInputElement>) => {
    const {
      currentTarget: { value },
    } = e;
    setregionValue(value);
  };

  //검색 후 결과 컴포넌트 클릭하면 해당 상세페이지로 연결
  const navigateToDetail = (spotId: number) => {
    navigate(`/detail/${spotId}`);
  };

  return (
    <>
      <div className="text-center py-6 text-4xl font-serif">
        지역으로 검색하기
      </div>
      <div className="grid grid-cols-5">
        {/* input type="date"의 min/max 속성은 선택할 수 있는 날짜의 최대/최소  min="2022-04-01" max="2022-04-30" 식으로 제한 */}
        <input
          type="date"
          min={todayString}
          max={lastDayString}
          className="ml-1 h-10 bg-white border border-gray-200 font-serif rounded-2xl shadow-md text-center"
          value={dateValue}
          onChange={handleSelectDate}
        ></input>

        <select
          name="sido"
          className="ml-1 h-10 bg-white border border-gray-200 rounded-2xl font-serif shadow-md text-center"
          onChange={handleSelectTime}
          value={timeValue}
        >
          {hours.map((item) => (
            <option value={item.hourNum} key={item.hourNum}>
              {item.hourString}
            </option>
          ))}
        </select>

        <div className="col-span-2 pl-3 pt-0.5 w-1/3 h-1/3">
          <img src={questionMark} className="w-8 h-8 mr-1" />
        </div>
      </div>

      <div className="bg-white border border-gray-200 rounded-2xl shadow-md my-2">
        <form className="grid grid-cols-12 gap-1" onSubmit={handleSearchButton}>
          <input
            className="rounded-3xl col-span-11 p-15 text-center text-2xl font-serif"
            type="text"
            value={regionValue}
            placeholder="지역을 검색해보세요!"
            onChange={handleSearchInput}
          ></input>
          <button className="col-span-1 rounded-3xl" type="submit">
            <img src={search} className="w-3/4 h3/4 p-3" alt="searchIcon" />
          </button>
        </form>
      </div>

      {searchResult === undefined ? ( //검색 결과 없으면
        <div className="text-center my-10 font-serif">검색 결과가 없어요</div>
      ) : (
        <div className="">
          <p className="font-serif">{searchRegion} 검색 결과</p>
          <div className="grid grid-cols-12 gap-10 mx-auto my-1">
            {searchResult.map((item) => (
              <div
                className="col-span-4"
                key={item.spot.spotId}
                onClick={() => navigateToDetail(item.spot.spotId)}
              >
                <SearchBox spotName={item.spot.spotName} grade={item.grade} />
              </div>
            ))}
          </div>
        </div>
      )}
    </>
  );
}

export default SearchMain;

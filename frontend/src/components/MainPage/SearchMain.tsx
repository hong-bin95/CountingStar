import React, { useState } from "react";
import search from "../../assets/search.png";
import SearchBox from "./SearchBox";
import questionMark from "../../assets/question.png";
import styled from "styled-components";
import axios from "axios";

type Props = {};

interface hourToString {
  hourNum: number;
  hourString: string;
}

type Sido = {
  id: number;
  name: string;
};

function SearchMain({}: Props) {
  //시간, 날짜, 시도, 구군
  const [timeValue, setTimeValue] = useState<string>("시간 선택");
  const [dateValue, setDateValue] = useState<string>("날짜 선택");
  const [sidoValue, setSidoValue] = useState<string>("시도 선택");
  const [gugunValue, setGugunValue] = useState<string>("");

  //시간(1) option(01시)로 변경하기
  let hours: hourToString[] = [];
  let obj: hourToString = { hourNum: 0, hourString: "시간 선택" };
  hours.push(obj);

  for (let i = 0; i < 24; i++) {
    let timeNum: number = i;
    let timeString: string = ("0" + i).slice(-2) + "시";

    let obj: hourToString = { hourNum: timeNum, hourString: timeString };

    hours.push(obj);
  }

  //날짜 제한 (오늘~10일 후)
  let date = new Date();
  let year = date.getFullYear();
  let month = ("0" + (date.getMonth() + 1)).slice(-2);
  let day = ("0" + date.getDate()).slice(-2);

  //3월 넘어가면
  let lastMonthNum = date.getMonth() + 1;
  let lastMonth = "";
  if (date.getDate() + 10 > 31) {
    lastMonth = ("0" + (lastMonthNum + 1)).slice(-2);
  }
  let lastDate = new Date(date.setDate(date.getDate() + 10));

  let lastDay = ("0" + lastDate.getDate()).slice(-2);

  let todayString = year + "-" + month + "-" + day;
  let lastDayString = year + "-" + lastMonth + "-" + lastDay;
  console.log(todayString);
  console.log(lastDayString);

  //시도 리스트
  let sidos: Sido[] = [
    { id: 100, name: "지역선택" },
    { id: 101, name: "서울특별시" },
    { id: 102, name: "광주광역시" },
    { id: 103, name: "대구광역시" },
    { id: 104, name: "대전광역시" },
    { id: 105, name: "부산광역시" },
    { id: 106, name: "울산광역시" },
    { id: 107, name: "인천광역시" },
    { id: 108, name: "강원도" },
    { id: 109, name: "경기도" },
    { id: 110, name: "경상남도" },
    { id: 111, name: "경상북도" },
    { id: 112, name: "전라남도" },
    { id: 113, name: "전라북도" },
    { id: 114, name: "충청남도" },
    { id: 115, name: "충청북도" },
    { id: 116, name: "제주특별자치도" },
    { id: 117, name: "세종특별자치시" },

    // "지역 선택",
    // "서울특별시",
    // "광주광역시",
    // "대구광역시",
    // "대전광역시",
    // "부산광역시",
    // "울산광역시",
    // "인천광역시",
    // "강원도",
    // "경기도",
    // "경상남도",
    // "경상북도",
    // "전라남도",
    // "전라북도",
    // "충청남도",
    // "충청북도",
    // "제주특별자치도",
    // "세종특별자치시",
  ];

  const handleSelectTime = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setTimeValue(e.target.value);
  };

  const handleSelectDate = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDateValue(e.target.value);
  };

  const handleSelectSido = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSidoValue(e.target.value);
  };

  const handleSearchButton = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // console.log(sidoValue);
    // console.log(gugunValue);
    // console.log(dateValue);
    // console.log(timeValue);

    if (sidoValue === "시도 선택") {
      alert("시/도를 선택해주세요");
    } else if (dateValue === "날짜 선택") {
      alert("날짜를 선택해주세요");
    } else if (timeValue === "시간 선택") {
      alert("시간을 선택해주세요");
    }

    let baseDateY = dateValue.slice(0, 4);
    let baseDateM = dateValue.slice(5, 7);
    let baseDateD = dateValue.slice(8, 10);

    // console.log(baseDateY);
    // console.log(baseDateM);
    // console.log(baseDateD);

    //request 보내야 하는 부분
    axios
      .get("https://counting-star.com/api/grade/", {
        params: {
          baseDateDay: baseDateD,
          baseDateHour: timeValue.toString(),
          baseDateMinute: "00",
          baseDateMonth: baseDateM.toString(),
          baseDateYear: baseDateY.toString(),
          keyword: gugunValue,
          limit: 18,
          searchType: "NAME",
        },
      })
      .then(function (response) {
        console.log(response.data);
      })
      .catch(function (err) {
        console.log(err);
      });
  };
  const handleSearchInput = (e: React.FormEvent<HTMLInputElement>) => {
    const {
      currentTarget: { value },
    } = e;
    setGugunValue(value);
  };

  return (
    <>
      <div className="text-center py-6 text-4xl font-serif">
        지역으로 검색하기
      </div>
      <div className="grid grid-cols-5">
        <select
          name="sido"
          className=" ml-1 h-10 bg-white border border-gray-200 rounded-2xl shadow-md text-center"
          onChange={handleSelectSido}
          value={sidoValue}
        >
          {sidos.map((item: Sido) => (
            <option value={item.name} key={item.id}>
              {item.name}
            </option>
          ))}
        </select>

        {/* input type="date"의 min/max 속성은 선택할 수 있는 날짜의 최대/최소  min="2022-04-01" max="2022-04-30" 식으로 제한 */}
        <input
          type="date"
          min={todayString}
          max={lastDayString}
          className="ml-1 h-10 bg-white border border-gray-200 rounded-2xl shadow-md text-center"
          value={dateValue}
          onChange={handleSelectDate}
        ></input>

        <select
          name="sido"
          className="ml-1 h-10 bg-white border border-gray-200 rounded-2xl shadow-md text-center"
          onChange={handleSelectTime}
          value={timeValue}
        >
          {hours.map((item) => (
            <option value={item.hourNum} key={item.hourNum}>
              {item.hourString}
            </option>
          ))}
        </select>

        <QuestionHover className="col-span-2  grid justify-items-end">
          <img src={questionMark} className="w-8 h-8 mr-1" />
        </QuestionHover>
      </div>

      <div className="bg-white border border-gray-200 rounded-2xl shadow-md my-2 ">
        <form className="grid grid-cols-12 gap-1" onSubmit={handleSearchButton}>
          <input
            className="rounded-3xl col-span-11 p-15 text-center text-2xl font-serif"
            type="text"
            value={gugunValue}
            placeholder="구/군을 검색해보세요!"
            onChange={handleSearchInput}
          ></input>
          <button className="col-span-1 rounded-3xl" type="submit">
            <img src={search} className="w-3/4 h3/4 p-3" alt="searchIcon" />
          </button>
        </form>
      </div>

      {"들어온 데이터의 개수 0이면" ? (
        <div className="text-center my-10 font-serif">검색 결과가 없어요</div>
      ) : (
        <div className="">
          <p className="font-serif">강원도 영월군 검색 결과</p>
          <div className="grid grid-cols-12 gap-10 mx-auto my-1">
            <div className="col-span-4">
              <SearchBox />
            </div>
            <div className="col-span-4">
              <SearchBox />
            </div>
            <div className="col-span-4">
              <SearchBox />
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default SearchMain;

const QuestionHover = styled.div`
  &:hover {
    background-color: yellow;
  }
`;

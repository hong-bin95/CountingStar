import React, { useState } from "react";
import search from "../../assets/search.png";
import SearchBox from "./SearchBox";
import Select from "react-select";

type Props = {};

interface hourToString {
  hourNum: number;
  hourString: string;
}

function SearchMain({}: Props) {
  //시간, 날짜, 시도, 구군
  const [timeValue, setTimeValue] = useState<string>("시간 선택");
  const [dateValue, setDateValue] = useState<string>("");
  const [sidoValue, setSidoValue] = useState<string>("");
  const [gugunValue, setGugunValue] = useState<string>("");

  //시간(1) option(01시)로 변경하기
  let hours: hourToString[] = [];
  // let obj: hourToString = { hourNum: 0, hourString: "시간 선택" };
  // hours.push(obj);

  for (let i = 1; i < 25; i++) {
    let timeNum: number = i;
    let timeString: string = ("0" + i).slice(-2) + "시";

    let obj: hourToString = { hourNum: timeNum, hourString: timeString };

    hours.push(obj);
  }

  const handleSelectTime = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setTimeValue(e.target.value);
  };

  return (
    <>
      <div className="text-center py-6 text-4xl font-serif">
        지역으로 검색하기
      </div>
      <select
        name="sido"
        className="w-1/6 ml-1 bg-white border border-gray-200 rounded-2xl shadow-md text-center"
        // onChange={handle}
      >
        <option value="selectSido" selected hidden>
          시/도 선택
        </option>
        <option value="option1">option1</option>
        <option value="option2">option2</option>
        <option value="option3">option3</option>
        <option value="option4">option4</option>
        <option value="option5">option5</option>
      </select>

      {/* input type="date"의 min/max 속성은 선택할 수 있는 날짜의 최대/최소  min="2022-04-01" max="2022-04-30" 식으로 제한 */}
      <input
        type="date"
        min=""
        max=""
        className="w-1/6 ml-1 bg-white border border-gray-200 rounded-2xl shadow-md text-center"
      ></input>

      <select
        name="sido"
        className="w-1/6 ml-1 bg-white border border-gray-200 rounded-2xl shadow-md text-center"
        onChange={handleSelectTime}
        value={timeValue}
      >
        {hours.map((item) => (
          <option value={item.hourNum} key={item.hourNum}>
            {item.hourString}
          </option>
        ))}
      </select>

      <div className="bg-white border border-gray-200 rounded-2xl shadow-md my-2 ">
        <form className="grid grid-cols-12 gap-1">
          <input className="rounded-3xl col-span-11 p-15 text-center text-2xl font-serif"></input>
          <button className="col-span-1 rounded-3xl">
            <img src={search} className="w-3/4 h3/4 p-3" alt="searchIcon" />
          </button>
        </form>
      </div>

      <div className="text-center my-10 font-serif">검색 결과가 없어요</div>

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
    </>
  );
}

export default SearchMain;

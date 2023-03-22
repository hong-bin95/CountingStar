import React from "react";
import search from "../../assets/search.png";

type Props = {};

function SearchMain({}: Props) {
  return (
    <>
      <div className="text-center py-6 text-4xl">지역으로 검색하기</div>
      <div>
        <select
          name="sido"
          className="w-1/6 ml-1 bg-white border border-gray-200 rounded-2xl shadow-md text-center"
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
      </div>
      <div className="bg-white border border-gray-200 rounded-2xl shadow-md my-2 ">
        <form className="grid grid-cols-12 gap-1">
          <input className="rounded-3xl col-span-11 p-15 text-center text-2xl"></input>
          <button className="col-span-1 rounded-3xl">
            <img src={search} className="w-3/4 h3/4 p-3" alt="searchIcon" />
          </button>
        </form>
      </div>

      <div className="text-center my-10">검색 결과가 없습니다.</div>

      <div className="">
        <p>강원도 영월군 검색 결과</p>
        <div></div>
      </div>
    </>
  );
}

export default SearchMain;

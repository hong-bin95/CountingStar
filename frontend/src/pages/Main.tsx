import React from "react";
import SearchMain from "../components/MainPage/SearchMain";
import ThisMonthMain from "../components/MainPage/ThisMonthMain";
import TodayMain from "../components/MainPage/TodayMain";
import WeekendMain from "../components/MainPage/WeekendMain";

type Props = {
  toggleMainVisibility: () => void;
};

function Main({ toggleMainVisibility }: Props) {
  return (
    <>
      <div className="container mx-auto mt-20 px-10 pt-10 pb-10 w-5/6 h-50 flex flex-col shadow-md rounded-3xl border border-gray-300 bg-white">
        <div className="my-4 rounded-2xl">
          <TodayMain toggleMainVisibility={toggleMainVisibility} />
        </div>
      </div>
      <div className="container mx-auto mt-10 px-10 w-5/6 h-50 flex flex-col shadow-md rounded-3xl border border-gray-300 bg-white">
        <div className="my-4 rounded-2xl">
          <WeekendMain />
        </div>
      </div>
      <div className="container mx-auto mt-10 px-10 w-5/6 h-50 flex flex-col shadow-md rounded-3xl border border-gray-300 bg-white">
        <div className="my-4 rounded-2xl">
          <ThisMonthMain />
        </div>
      </div>
      <div className="container mx-auto mt-10 px-10 w-5/6 h-50 flex flex-col shadow-md rounded-3xl border border-gray-300 bg-white">
        <div className="my-4 rounded-2xl bg-pink-50">
          <SearchMain />
        </div>
      </div>
    </>
  );
}

export default Main;

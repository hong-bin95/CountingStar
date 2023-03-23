import React from "react";
import Logo from "../Logo";
import TodayBox from "../../components/MainPage/TodayBox";

type Props = {};

function TodayMain({}: Props) {
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
      <div className="grid grid-cols-12 gap-10 mx-auto my-1 ">
        <div className="col-span-4">
          <TodayBox />
        </div>
        <div className="col-span-4">
          <TodayBox />
        </div>
        <div className="col-span-4">
          <TodayBox />
        </div>
      </div>
    </>
  );
}

export default TodayMain;

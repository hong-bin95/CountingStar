import React from "react";

import MoonImage from "../../assets/full-moon.png";
import Star_1 from "../../assets/1_star.png";
import Star_2 from "../../assets/2_star.png";
import Star_3 from "../../assets/3_star.png";
import Star_4 from "../../assets/4_star.png";
import Star_5 from "../../assets/5_star.png";

const GradeInfo = (Props: any) => {
  return (
    <div className="font-serif fixed top-4 left-6 z-5 w-48 h-54 bg-white border-4 border-black shadow-md rounded">
      <div className="flex items-center">
        <img src={MoonImage} alt="Moon" className="w-8 h-8 m-1" />
        <div className="ml-2"> 아무 것도 안보여요</div>
      </div>
      <div className="flex items-center">
        <img src={Star_1} alt="1 Star" className="w-8 h-8 m-1" />
        <div className="ml-2"> 조금 보여요</div>
      </div>
      <div className="flex items-center">
        <img src={Star_2} alt="2 Star" className="w-8 h-8 m-1" />
        <div className="ml-2"> 조금 잘 보여요</div>
      </div>
      <div className="flex items-center">
        <img src={Star_3} alt="3 Star" className="w-8 h-8 m-1" />
        <div className="ml-2"> 잘 보여요</div>
      </div>
      <div className="flex items-center">
        <img src={Star_4} alt="4 Star" className="w-8 h-8 m-1" />
        <div className="ml-2"> 아주 잘 보여요</div>
      </div>
      <div className="flex items-center">
        <img src={Star_5} alt="5 Star" className="w-8 h-8 m-1" />
        <div className="ml-2"> 엄청 잘 보여요</div>
      </div>
    </div>
  );
};

export default GradeInfo;

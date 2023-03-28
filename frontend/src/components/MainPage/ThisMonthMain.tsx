import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";
import ThisMonthBox from "./ThisMonthBox";

type Props = {};

interface stella {
  constellationId: number;
  constellationName: string;
}

function ThisMonthMain(props: Props) {
  const [constellaList, setConstellaList] = useState<Array<stella>>([]);

  useEffect(() => {
    let now = new Date();
    let year = now.getFullYear().toString();
    let month = ("0" + (now.getMonth() + 1)).slice(-2);

    axios
      .get("https://counting-star.com/api/constellation/rank", {
        params: {
          baseDateYear: "2001",
          baseDateMonth: "12",
          limit: 5,
          // baseDateYear: { year },
          // baseDateMonth: { month },
          // limit: 5,
        },
      })
      .then(function (response) {
        console.log(response.data);
        console.log("구분");
        setConstellaList(response.data.data);
        console.log("리스트 저장한거 출력");
        console.log(constellaList);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  return (
    <>
      <div className="text-4xl py-6 text-center font-serif">
        이번 달의 별자리
      </div>

      <div className="grid grid-cols-12 gap-10 mx-auto my-1 ">
        {constellaList.map((star, idx) => (
          <div className="col-span-4" key={idx}>
            <ThisMonthBox
              stellaName={star.constellationName}
              cnt={star.constellationId}
            />
          </div>
        ))}
      </div>
    </>
  );
}

export default ThisMonthMain;

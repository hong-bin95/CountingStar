import React, {useState, useEffect} from 'react';
import { useSelector } from 'react-redux';
import { DetailsData} from '../../store/DetailsSlice';
import starScore from "../../assets/fiveStar.png";
import axios from 'axios';

function DetailsPoint() {
    
    const date = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.date);
    const year = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.year);
    const month = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.month);
    const hour = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.hour);
    const spotId = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.spotId);

    const [score, setScore] = useState<number>(0);

    useEffect(()=>{
        axios
        .get(`https://counting-star.com/api/grade/?baseDateDay=${date}&baseDateHour=00&baseDateMinute=00&baseDateMonth=${month}&baseDateYear=${year}&keyword=${spotId}&limit=1&searchType=ID`,{
        // .get(`https://counting-star.com/api/grade/?baseDateDay=${date}&baseDateHour=${hour}&baseDateMinute=00&baseDateMonth=${month}&baseDateYear=${year}&keyword=${spotId}&limit=1&searchType=ID`,{
        })
        .then((res) => {
            setScore(res.data.data[0].grade);
        })
        .catch((err) => {
          console.log(err);
        });
    },[date, hour]);

    function repeatStar(score: number): JSX.Element[] {
        let arr = [];
        for (let i = 0; i < score; i++) {
          arr.push(
            <p key={i}>
              <img src={starScore} className="w-6 mx-2" />
            </p>
          );
        }
        return arr;
      }

    return (
        <div>
            <div className="mt-10 mb-10 flex justify-center">별 관측 가능 점수</div>
            <div className="flex justify-center pt-3 pb-24">{repeatStar(score)}</div>
        </div>
    );
}

export default DetailsPoint;
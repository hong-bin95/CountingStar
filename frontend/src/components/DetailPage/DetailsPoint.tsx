import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { DetailsData} from '../../store/DetailsSlice';
import starScore from "../../assets/fiveStar.png";
import axios from 'axios';

function DetailsPoint() {
    
  const [year, setYear] = useState<number>(new Date().getFullYear());
  const [month, setMonth] = useState<number>(new Date().getMonth() +1);
  const [date, setDate] = useState<number>(new Date().getDate());
  const [hour, setHour] = useState<number>(new Date().getHours()+1);
  const { spotId } = useParams<{ spotId: string | undefined }>();

  const nowDate = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.date);
  const nowYear = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.year);
  const nowMonth = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.month);
  const nowHour = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.hour);

  useEffect(()=>{
      setYear(Number(nowYear));
      setMonth(Number(nowMonth));
      setDate(Number(nowDate));
      setHour(Number(nowHour));
  },[nowDate, nowYear, nowMonth, nowHour,]);

    const [score, setScore] = useState<number>(0);

    useEffect(()=>{
        axios
        .get(`https://counting-star.com/api/grade/?baseDateDay=${date<10?`0${date}`:date}&baseDateHour=00&baseDateMinute=00&baseDateMonth=${month<10?`0${month}`:month}&baseDateYear=${year}&keyword=${spotId}&limit=1&searchType=ID`,{
        // .get(`https://counting-star.com/api/grade/?baseDateDay=${date<10?`0${date}`:date}&baseDateHour=${hour<10?`0${hour}`:hour}&baseDateMinute=00&baseDateMonth=${month<10?`0${month}`:month}&baseDateYear=${year}&keyword=${spotId}&limit=1&searchType=ID`,{
        })
        .then((res) => {
            setScore(res.data.data[0].grade);
        })
        .catch((err) => {
          console.log(err);
        });
    },[date]);
    // },[date, hour]);

    function repeatStar(score: number): JSX.Element[] {
        let arr = [];
        for (let i = 0; i < score; i++) {
          arr.push(
            <p key={i}>
              <img src={starScore} alt='별' className="w-6 mx-2" />
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
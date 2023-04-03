import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { DetailsData } from '../../store/DetailsSlice';
import axios from 'axios';
import angry from '../../assets/angry.png';
import heartEyes from '../../assets/heart-eyes.png';
import sad from '../../assets/sad.png';
import smile from '../../assets/smile.png';

function DetailsDust() {

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

    const [dust, setDust] = useState<string>('');

    useEffect(()=>{
        axios
        .get(`https://counting-star.com/api/weather/dust?baseDateYear=${year}&baseDateMonth=${month<10?`0${month}`:month}&baseDateDay=${date<10?`0${date}`:date}&baseDateHour=${hour<10?`0${hour}`:hour}&spotId=${spotId}`,{})
        .then((res) => {
            setDust(res.data.data.dustCondition);
        })
        .catch((err) => {
          console.log(err);
        });
    },[date, hour]);
    
    // 나쁨 보통 좋음
    const style={
        display: 'flex',
        marginTop: '15px',
        marginLeft: '60px',
        width: '180px',
        height: '180px',
        borderRadius: '90px',
    }

    return (
        <div>
            <img style={style} src={
                dust===' 나쁨'?sad:
                dust===' 보통'?smile:
                dust===' 좋음'?heartEyes:
                angry} alt="미세먼지 정도"/>
            <div className="flex justify-center mt-5">미세먼지 {dust}</div> 
        </div>
    );
}

export default DetailsDust;
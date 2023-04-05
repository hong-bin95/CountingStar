import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { DetailsData } from '../../types/DetailsType';
import { useDispatch, useSelector } from 'react-redux';
import { updateDust } from '../../store/DetailsSlice';
import axios from 'axios';
import angry from '../../assets/dustEmoji/angry.png';
import heartEyes from '../../assets/dustEmoji/heart-eyes.png';
import sad from '../../assets/dustEmoji/sad.png';
import smile from '../../assets/dustEmoji/smile.png';
import question from '../../assets/question.png';

function DetailsDust() {

    const dispatch = useDispatch();

    const { spotId } = useParams<{ spotId: string | undefined }>();

    const [year, setYear] = useState<number>(new Date().getFullYear());
    const [month, setMonth] = useState<number>(new Date().getMonth() +1);
    const [date, setDate] = useState<number>(new Date().getDate());
    const [hour, setHour] = useState<number>(new Date().getHours()+1);
    const [dust, setDust] = useState<string>('');

    const nowDay = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.day);
    const nowDate = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.date);
    const nowYear = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.year);
    const nowMonth = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.month);
    const nowHour = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.hour);

    useEffect(()=>{
        setYear(Number(nowYear));
        setMonth(Number(nowMonth));
        setDate(Number(nowDate));
        setHour(Number(nowHour));
    },[nowDate, nowYear, nowMonth, nowHour,]);

    useEffect(()=>{
        if(nowDay<3){
        axios
        .get(`https://counting-star.com/api/weather/dust?baseDateYear=${year}&baseDateMonth=${month<10?`0${month}`:month}&baseDateDay=${date<10?`0${date}`:date}&baseDateHour=${hour<10?`0${hour}`:hour}&spotId=${spotId}`,{})
        .then((res) => {
            setDust(res.data.data.dustCondition);
            dispatch(updateDust(res.data.data.dustCondition));
        })
        .catch((err) => {
          console.log(err);
        });
    }
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
                nowDay>=3?question:
                dust===' 나쁨'?sad:
                dust===' 보통'?smile:
                dust===' 좋음'?heartEyes:
                angry} alt="미세먼지 정도"/>
            <div className="flex justify-center mt-5">{nowDay<3?`미세먼지 ${dust}`:`???`}</div> 
        </div>
    );
}

export default DetailsDust;
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { updateWeather } from '../../store/DetailsSlice';
import { DetailsData } from '../../types/DetailsType';
import axios from 'axios';
import sunny from '../../assets/weather/sunny.jpg';
import rain from '../../assets/weather/rain.jpg';
import cloudy from '../../assets/weather/cloudy.jpg';
import snow from '../../assets/weather/snow.png';

function DetailsWeather() {

    const dispatch = useDispatch();

    const { spotId } = useParams<{ spotId: string | undefined }>();

    const [year, setYear] = useState<number>(new Date().getFullYear());
    const [month, setMonth] = useState<number>(new Date().getMonth() +1);
    const [date, setDate] = useState<number>(new Date().getDate());
    const [hour, setHour] = useState<number>(new Date().getHours()+1);
    const [weather, setWeather] = useState<string>('');

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
        axios
        .get(`https://counting-star.com/api/weather/condition?baseDateYear=${year}&baseDateMonth=${month<10?`0${month}`:month}&baseDateDay=${date<10?`0${date}`:date}&baseDateHour=${hour<10?`0${hour}`:hour}&spotId=${spotId}`,{})
        .then((res) => {    
            setWeather(res.data.data.weatherCondition);
            dispatch(updateWeather(res.data.data.weatherCondition));        
        })
        .catch((err) => {
          console.log(err);
        });
    },[date, hour]);

    // 날씨 : 비/비 혹은 눈/눈/소나기/흐림/구름많음/맑음
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
                weather.indexOf('소나기')!==-1?rain:
                weather.indexOf('비')!==-1?rain:
                weather.indexOf('맑음')!==-1?sunny:
                weather.indexOf('눈')!==-1?snow:
                weather.indexOf('흐림')!==-1?cloudy:
                weather.indexOf('구름많음')!==-1?cloudy:
                undefined} alt="날씨"/>
            <div className="flex justify-center mt-5">{weather}</div> 
        </div>
    );
}

export default DetailsWeather;
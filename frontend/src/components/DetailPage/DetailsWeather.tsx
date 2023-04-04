import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { DetailsData, updateWeather} from '../../store/DetailsSlice';
import axios from 'axios';
import sunny from '../../assets/sunny.jpg';
import rain from '../../assets/rain.jpg';
import cloudy from '../../assets/cloudy.jpg';
import snow from '../../assets/snow.png';

function DetailsWeather() {

    const dispatch = useDispatch();

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

    const [weather, setWeather] = useState<string>('');

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
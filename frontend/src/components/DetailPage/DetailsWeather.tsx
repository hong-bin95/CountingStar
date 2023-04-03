import React, {useState, useEffect} from 'react';
import { useSelector } from 'react-redux';
import { DetailsData} from '../../store/DetailsSlice';
import axios from 'axios';
import sunny from '../../assets/sunny.jpg';
import rain from '../../assets/rain.jpg';
import cloudy from '../../assets/cloudy.jpg';
import snow from '../../assets/snow.png';

function DetailsWeather() {

    const date = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.date);
    const year = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.year);
    const month = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.month);
    const hour = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.hour);
    const spotId = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.spotId);

    const [weather, setWeather] = useState<string>('');

    useEffect(()=>{
        axios
        .get(`https://counting-star.com/api/weather/condition?baseDateYear=${year}&baseDateMonth=${month}&baseDateDay=${date}&baseDateHour=${hour}&spotId=${spotId}`,{})
        .then((res) => {
            setWeather(res.data.data.weatherCondition);
            
        })
        .catch((err) => {
          console.log(err);
        });
    },[date, hour, weather]);
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
            <img style={style} src={weather.slice(0,1)===('비'||'소')?rain:weather.slice(0,1)===('맑')?sunny:weather.slice(0,1)==='눈'?snow:cloudy} alt="날씨"/>
            <div className="flex justify-center mt-5">{weather}</div> 
        </div>
    );
}

export default DetailsWeather;
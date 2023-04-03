import React, {useState, useEffect} from 'react';
import { useSelector } from 'react-redux';
import { DetailsData } from '../../store/DetailsSlice';
import axios from 'axios';
import angry from '../../assets/angry.png';
import heartEyes from '../../assets/heart-eyes.png';
import sad from '../../assets/sad.png';
import smile from '../../assets/smile.png';

function DetailsDust() {

    const date = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.date);
    const year = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.year);
    const month = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.month);
    const hour = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.hour);
    const spotId = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.spotId);

    const [dust, setDust] = useState<string>('');

    useEffect(()=>{
        axios
        .get(`https://counting-star.com/api/weather/dust?baseDateYear=${year}&baseDateMonth=${month}&baseDateDay=${date}&baseDateHour=${hour}&spotId=${spotId}`,{})
        .then((res) => {
            setDust(res.data.data.dustCondition);
            setDust('좋음');
        })
        .catch((err) => {
          console.log(err);
        });
    },[date, hour, dust]);
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
            <img style={style} src={dust==='나쁨'?sad:dust==='보통'?smile:dust==='좋음'?heartEyes:angry} alt="미세먼지 정도"/>
            <div className="flex justify-center mt-5">미세먼지 {dust}</div> 
        </div>
    );
}

export default DetailsDust;
import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import styled from 'styled-components';
import { useDispatch, useSelector } from 'react-redux';
import { DetailsData, updateMoon } from '../../store/DetailsSlice';
import axios from 'axios';

const TextContainer = styled.div`
  text-align: center;
`
function DetailsMoon() {
    const moon = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.moon);
    const [year, setYear] = useState<number>(new Date().getFullYear());
    const [month, setMonth] = useState<number>(new Date().getMonth() +1);
    const [date, setDate] = useState<number>(new Date().getDate());
  
    const nowDate = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.date);
    const nowYear = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.year);
    const nowMonth = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.month);
   
    useEffect(()=>{
        setYear(Number(nowYear));
        setMonth(Number(nowMonth));
        setDate(Number(nowDate));
    },[nowDate, nowYear, nowMonth,]);
    
    const [moonName, setMoonName] = useState<string>('');
    const dispatch = useDispatch();

    useEffect(()=>{
        axios
        .get(`https://counting-star.com/api/moon/${year}${month<10?`0${month}`:month}${date<10?`0${date}`:date}`,{
        })
        .then((res) => {
            dispatch(updateMoon(res.data.data));
            setMoonName(decodeURI(res.data.data.substring(33, )));
        })
        .catch((err) => {
          console.log(err);
        });
      },[date,]);
      
    return (
        <div>
            <div className="grid justify-items-center mt-5 mb-8 ">
              <img className="w-40"src={moon}></img>
            </div>
            <TextContainer>
            <div>{moonName.slice(0,3)==='차가는'?'차가는 달':moonName.slice(0,3)==='기울어'?'기울어가는 달':moonName.slice(0,3)}</div>
            </TextContainer>
        </div>
    );
}

export default DetailsMoon;
import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { DetailsData } from '../../types/DetailsType';
import { useSelector } from 'react-redux';
import axios from 'axios';

const TextContainer = styled.div`
  text-align: center;
`
function DetailsMoon() {
    
    const [year, setYear] = useState<number>(new Date().getFullYear());
    const [month, setMonth] = useState<number>(new Date().getMonth() +1);
    const [date, setDate] = useState<number>(new Date().getDate());
    const [moonName, setMoonName] = useState<string>('');
    const [moon, setMoon] = useState<string>('');

    const nowDate = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.date);
    const nowYear = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.year);
    const nowMonth = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.month);
   
    useEffect(()=>{
        setYear(Number(nowYear));
        setMonth(Number(nowMonth));
        setDate(Number(nowDate));
    },[nowDate, nowYear, nowMonth,]);
    

    useEffect(()=>{
        axios
        .get(`https://counting-star.com/api/moon/${year}${month<10?`0${month}`:month}${date<10?`0${date}`:date}`,{
        })
        .then((res) => {
            setMoon(res.data.data);
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
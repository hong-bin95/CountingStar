import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import { useSelector } from 'react-redux';
import { DetailsData } from '../../store/DetailsSlice';

const ImgContainer = styled.div`
  display: flex;
  justify-content: center;
`
const TextContainer = styled.div`
  text-align: center;
`
function DetailsMoon() {
    const moon = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.moon);
    const date = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.date);
    const [moonName, setMoonName] = useState<string>(decodeURI(moon).substr(33,3));

    useEffect(()=>{
      setMoonName(decodeURI(moon).substr(33,3));
      if(moonName==='차가는') setMoonName(moonName+' 달');
      else if(moonName==='기울어') setMoonName(moonName+'가는 달');
    },[date]);
    
    return (
        <div>
            <ImgContainer>
            <img src={moon}></img>
            </ImgContainer>
            <TextContainer>
            <div>{moonName}</div>
            </TextContainer>
        </div>
    );
}

export default DetailsMoon;
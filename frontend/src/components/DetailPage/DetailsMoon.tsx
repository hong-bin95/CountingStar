import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import { useDispatch, useSelector } from 'react-redux';
import { DetailsData, updateMoon } from '../../store/DetailsSlice';
import axios from 'axios';

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
    const year = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.year);
    const month = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.month);
    const [moonName, setMoonName] = useState<string>('');
    const dispatch = useDispatch();

    useEffect(()=>{
        axios
        .get(`https://counting-star.com/api/moon/${year}${month}${date}`,{
        })
        .then((res) => {
            dispatch(updateMoon(res.data.data));
            setMoonName(decodeURI(res.data.data.substring(33, )));
        })
        .catch((err) => {
          console.log(err);
        });
      },[date]);
      
    return (
        <div>
            <ImgContainer>
            <img src={moon}></img>
            </ImgContainer>
            <TextContainer>
            <div>{moonName.slice(0,3)==='차가는'?'차가는 달':moonName.slice(0,3)==='기울어'?'기울어가는 달':moonName.slice(0,3)}</div>
            </TextContainer>
        </div>
    );
}

export default DetailsMoon;
import React, {useState, useEffect} from 'react';
import { useSelector, useDispatch } from 'react-redux';
import styled from 'styled-components';
import {updateHour, DetailsData} from '../../store/DetailsSlice';
import UpBtn from '../../assets/arrowsRight.png';
import DownBtn from '../../assets/arrowsLeft.png';

const DateContainer = styled.div`
    display: flex;
    justify-content: space-between;
    padding-top: 100px;
`;

function DetailsHour() {

    const dispatch = useDispatch();
    const day = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.day);
    
    const [today, setToday] = useState<Date>(new Date());
    const [nowHour, setNowHour] = useState<number>(today.getHours()+1);
    const [hour, setHour] = useState<number>(today.getHours()+1);
    const [noon, setNoon] = useState<string>('');

    const styled = {
        height : '25px',
        width : '25px',
        marginTop : '10px'
    }

    useEffect(() => {
        setHour(today.getHours()+1);   
    },[day]);
    
    useEffect(()=>{
        setNoon(hour>=12? '오후' : '오전');
        dispatch(updateHour(hour));
    },[hour]);

    const moveUp = () => {
        setHour(hour!==23?hour+1:hour);
    }
    const moveDown = () => {
        if(day===0 && nowHour === hour) return;
        setHour(hour!==0?hour-1:hour);
    }

    return (
        <DateContainer>
            <img style={styled} src={DownBtn} alt="do" onClick={moveDown}/>
            <div> {noon} <br/> {hour>12?hour-12:hour===0?12:hour}시 </div>
            <img style={styled} src={UpBtn} alt="up" onClick={moveUp}/>
        </DateContainer>
    );
}

export default DetailsHour;
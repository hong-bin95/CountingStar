import React, { useState, useEffect } from 'react';
import { DetailsData } from '../../types/DetailsType';
import { useSelector, useDispatch } from 'react-redux';
import { updateDay, updateYear, updateMonth, updateDate } from '../../store/DetailsSlice';
import styled from 'styled-components';
import UpBtn from '../../assets/arrowsRight.png';
import DownBtn from '../../assets/arrowsLeft.png';

const DateContainer = styled.div`
    display: flex;
    justify-content: space-between;
    padding-top: 90px;
`;

function DetailsDate() {

    const dispatch = useDispatch();
    
    const today = new Date();
    const [newDate, setNewDate] = useState<Date>(today);
    const [year, setYear] = useState<number>(newDate.getFullYear());
    const [month, setMonth] = useState<number>(newDate.getMonth() +1);
    const [date, setDate] = useState<number>(newDate.getDate());
    
    const day = useSelector((state:{detailsSlice:DetailsData}) => state.detailsSlice.day);
    
    useEffect(() => {
        dispatch(updateYear(String(year)));
        dispatch(updateMonth(month>=10?String(month):`0${month}`));
        dispatch(updateDate(date>=10?String(date):`0${date}`));
    },[year, month, date]);

    
    const dateUp = () => {
        if(day === 10) return;
        dispatch(updateDay(day+1));
        setNewDate(new Date(newDate.setDate(newDate.getDate() + 1)));
        setYear(newDate.getFullYear());
        setMonth(newDate.getMonth() +1);
        setDate(newDate.getDate());
    }
    
    const dateDown = () => {
        if(day === 0) return;
        dispatch(updateDay(day-1));
        setNewDate(new Date(newDate.setDate(newDate.getDate() - 1)));
        setYear(newDate.getFullYear());
        setMonth(newDate.getMonth() +1);
        setDate(newDate.getDate());
    }

    const style = {
        height : '25px',
        width : '25px',
        marginTop: '20px',
    };
    
    return (
        <DateContainer>
            <img style={style} src={DownBtn} alt="down" onClick={dateDown}/>
            <div> {year}년 <br/>   {month}월 <br/> {date}일</div>
            <img style={style} src={UpBtn} alt="up" onClick={dateUp}/>
        </DateContainer>
    );
}

export default DetailsDate;
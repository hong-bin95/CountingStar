import React, {useState, useEffect} from 'react';
import styled from 'styled-components';
import UpBtn from '../assets/UpBtn.png';
import DownBtn from '../assets/DownBtn.png';

const DateContainer = styled.div`
    height: 100%;
    display: flex;
    align-content: center;
    justify-content: space-between;
`;

function DetailsHour() {

    const [today, setToday] = useState<Date>(new Date());
    const [hour, setHour] = useState<number>(today.getHours());
    const [noon, setNoon] = useState<string>("오전");
    useEffect(() => {
        if(hour === 0) setHour(12);
        else if(hour >= 12) {
            setNoon("오후");
            if(hour>12) setHour(hour - 12);
        }
    }, [])

    const styled = {
        height : '25px',
        width : '25px',
    }

    const moveUp = () => {
        if(hour === 12) {
            setHour(1);
            if(noon==="오전") setNoon("오후");
            else setNoon("오전");
        }
        else setHour(hour + 1);
    }
    const moveDown = () => {
        if(hour === 1) {
            setHour(12);
            if(noon==="오전") setNoon("오후");
            else setNoon("오전");
        }
        else setHour(hour - 1);
    }
    const [wishHour, setWishHour] = useState<string>(`${noon}-${hour}`);
    useEffect(() => {
        setWishHour(`${noon}-${hour}`);
    }, [noon, hour]);

    return (
        <DateContainer>
            <img style={styled} src={DownBtn} alt="do" onClick={moveDown}/>
            <div> {noon} <br/> {hour}시 </div>
            <img style={styled} src={UpBtn} alt="up" onClick={moveUp}/>
        </DateContainer>
    );
}

export default DetailsHour;
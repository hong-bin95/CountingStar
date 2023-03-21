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

function DetailsDate() {

    let today = new Date();
    const [year, setYear] = useState<number>(today.getFullYear());
    const [month, setMonth] = useState<number>(today.getMonth() +1);
    const [date, setDate] = useState<number>(today.getDate());

    const styled = {
        height : '25px',
        width : '25px',
    }

    const moveUp = () => {
        if(month === 12 && date === 31){ 
            setMonth(1);
            setDate(1);
            setYear(year + 1);
        } 
        else {
            if( (month===1 || month===3 || month===5 || month===7 || month===8 || month===10 )&&date === 31){
                setDate(1);
                setMonth(month+1);
            }
            else if( (month===4 || month===6 || month===9 || month===11 )&&date === 30){
                setDate(1);
                setMonth(month+1);

            }
            else if( ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0) && month === 2 && date === 28){
                setDate(29);
            }
            else if (month === 2 && date === 28){
                setDate(1);
                setMonth(month+1);
            }
            else{
                setDate(date+1);
            }    
        }
    }

    const moveDown = () => {
        if(month === 1 && date === 1){ 
            setMonth(12);
            setDate(31);
            setYear(year - 1);
        } 
        else {
            if( (month===2 || month===4 || month===6 || month===8 || month===9 || month===11 )&&date === 1){
                setDate(31);
                setMonth(month-1);
            }
            else if( (month===5 || month===7 || month===10 || month===12 )&&date === 1){
                setDate(30);
                setMonth(month-1);

            }
            else if( ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0) && month === 3 && date === 1){
                setMonth(month-1);
                setDate(29);
            }
            else if (month === 3 && date === 1){
                setDate(28);
                setMonth(month-1);
            }
            else{
                setDate(date-1);
            }    
        }
    }

    const [wishDate, setWishDate] = useState<string>(`${year}-${month}-${date}`);
    useEffect(() => {
        setWishDate(`${year}-${month}-${date}`);
    }, [year, month, date]);

    return (
        <DateContainer>
            <img style={styled} src={DownBtn} alt="do" onClick={moveDown}/>
            <div> {year}년 <br/>   {month}월 <br/> {date}일</div>
            <img style={styled} src={UpBtn} alt="up" onClick={moveUp}/>
        </DateContainer>
    );
}

export default DetailsDate;
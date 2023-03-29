import React, {useState, useEffect} from 'react';
import styled, { keyframes } from 'styled-components';
import Logo from '../Logo';
import DetailsDate from './DetailsDate';
import DetailsPoint from './DetailsPoint';
import DetailsWeather from './DetailsWeather';
import DetailsHour from './DetailsHour';
import DetailsDust from './DetailsDust';
import DetailsMoon from './DetailsMoon';
import PlaceTitle from './PlaceTitle';
import ContainerButton from './ContainerButton';
import { useDispatch, useSelector } from 'react-redux';
import { updateMoon, updateSpotId, updateSpotName, DetailsData} from '../../store/DetailsSlice';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const slideUp = keyframes`
from {
    transform: translateY(200px);
}
`;

const MainContainer = styled.div`
border: 1px solid gray;
border-radius: 10px;
padding: 10px;
margin: 0 auto;
width: 1190px;

position: relative;
top: 720px;

animation-duration: 1s;
animation-timing-function: ease-out;
animation-name: ${slideUp};
animation-fill-mode: forwards;
`;

const TopContainer = styled.div`
display: flex;
margin-top: 40px;
justify-content: space-between;
height: 80px;
`;

const BottomContainer = styled.div`
display: flex;
flex-wrap : wrap;
`;

const ContentsContainer = styled.div`
border: 1px solid gray;
border-radius: 10px;
padding: 10px;
margin: 34px;
height: 270px;
width: 320px;
`;


function DetailsMain() {
    
    const dispatch = useDispatch();

    const [scroll, setScroll] = useState<number>(1000);
    const { spotId } = useParams<{ spotId: string | undefined }>();
    const [spotIdNumber, setSpotIdNumber] = useState<number>(0);

    useEffect(() => {
      if (spotId) {
        setSpotIdNumber(parseInt(spotId, 10));
      }
    }, [spotId]);
    
    const year = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.year);
    const month = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.month);
    const date = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.date);
    const hour = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.hour);
  
    useEffect(() => {
    if (spotIdNumber!==0) {
      axios
        .get(`https://counting-star.com/api/spot/${spotIdNumber}`,{
        })
        .then((res) => {
            console.log(res);
            dispatch(updateSpotId(spotIdNumber));
            dispatch(updateSpotName(res.data.data.spotName));

        })
        .catch((err) => {
            console.log(err);
        });
    }
    },[spotIdNumber]);
    
    useEffect(()=>{
        const temp = year + month + date;
        console.log(temp);
        axios
        .get(`https://counting-star.com/api/moon/${temp}`,{
        })
        .then((res) => {
            // console.log(res.data.data);
            dispatch(updateMoon(res.data.data));
        })
        .catch((err) => {
            console.log(err);
        });
    },[date])

    const onClick = () => {

        if(scroll===1000){
            window.scrollTo(0, 1000);
            window.scrollTo({
            top: 1000,
            left: 1000,
            behavior: "smooth"
            })
        setScroll(scroll-1000);
        }
        else {
            window.scrollTo(1000,0);
            window.scrollTo({
            top: 0,
            left: 1000,
            behavior: "smooth"
            })
        setScroll(scroll+1000);
        }
    }

    return (
        <div>
            <MainContainer>
                <TopContainer>
                    <Logo />
                    <PlaceTitle></PlaceTitle>
                    <ContainerButton onClick={onClick}></ContainerButton>
                </TopContainer>
                <BottomContainer>
                    <ContentsContainer>
                        <DetailsDate></DetailsDate>
                    </ContentsContainer>
                    <ContentsContainer>
                        <DetailsPoint></DetailsPoint>
                    </ContentsContainer>
                    <ContentsContainer>
                        <DetailsWeather></DetailsWeather>
                    </ContentsContainer>                    
                    <ContentsContainer>
                        <DetailsHour></DetailsHour>
                    </ContentsContainer>                    
                    <ContentsContainer>
                        <DetailsDust></DetailsDust>
                    </ContentsContainer>                    
                    <ContentsContainer>
                        <DetailsMoon></DetailsMoon>
                    </ContentsContainer>
                </BottomContainer>
            </MainContainer>

        </div>
    );
}

export default DetailsMain;
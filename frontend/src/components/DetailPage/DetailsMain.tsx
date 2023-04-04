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
import { updateSpotId, updateSpotName, DetailsData } from '../../store/DetailsSlice';
import { useParams } from 'react-router-dom';
import background from '../../assets/nightSkyExample.jpg';
import DustBackground from '../../assets/DustBackground.jpg';
import CloudyBackground from '../../assets/CloudyBackground.jpg';
import RainBackground from '../../assets/rain.gif';
import SnowBackground from '../../assets/snow.gif';
import axios from 'axios';

const slideUp = keyframes`
from {
    transform: translateY(300px);
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
margin-top: 22px;
justify-content: space-between;
height: 80px;
`;

const BottomContainer = styled.div`
display: flex;
flex-wrap : wrap;
`;

const ContentsContainer = styled.div`
border: 1px solid silver;
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
    
    useEffect(() => {
    if (spotIdNumber!==0) {
      axios
        .get(`https://counting-star.com/api/spot/${spotIdNumber}`,{
        })
        .then((res) => {
            dispatch(updateSpotId(spotIdNumber));
            dispatch(updateSpotName(res.data.data.spotName));

        })
        .catch((err) => {
            console.log(err);
        });
    }
    },[spotIdNumber]);

    const onClick = () => {
        if(scroll===1000){
            window.scrollTo({
            top: 700,
            left: 0,
            behavior: 'smooth',
            })
        setScroll(scroll-1000);
        }
        else {
            window.scrollTo({
            top: 0,
            left: 0,
            behavior: 'smooth'
            })
        setScroll(scroll+1000);
        }
    }

    const dust = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.dust);
    const weather = useSelector((state:{DetailsSlice:DetailsData}) => state.DetailsSlice.weather);
    
    const style = {
        backgroundImage: `url(${
            weather.indexOf('흐림')!==-1?CloudyBackground:
            weather.indexOf('구름많음')!==-1?CloudyBackground:
            weather.indexOf('맑음')!==-1 && dust==='나쁨'?DustBackground:
            weather.indexOf('맑음')!==-1?background:
            (weather.indexOf('소나기')!==-1 || weather.indexOf('비')!==-1)?RainBackground:
            weather.indexOf('눈')!==-1?SnowBackground:background
        })`,
        backgroundRepeat : 'no-repeat',
        backgroundSize : 'cover',
        height: '1550px',
    }

    return (
        <div style={style}>
            <MainContainer className="font-serif drop-shadow-lg bg-gray-100">
                <TopContainer className="ml-10 mr-10">
                    <Logo></Logo>
                    <PlaceTitle ></PlaceTitle>
                    <ContainerButton onClick={onClick}></ContainerButton>
                </TopContainer>
                <BottomContainer>
                    <ContentsContainer className="shadow-md drop-shadow-lg">
                        <DetailsDate></DetailsDate>
                    </ContentsContainer>
                    <ContentsContainer className="shadow-md drop-shadow-lg">
                        <DetailsPoint></DetailsPoint>
                    </ContentsContainer>
                    <ContentsContainer className="shadow-md drop-shadow-lg">
                        <DetailsWeather></DetailsWeather>
                    </ContentsContainer>                    
                    <ContentsContainer className="shadow-md drop-shadow-lg">
                        <DetailsHour></DetailsHour>
                    </ContentsContainer>                    
                    <ContentsContainer className="shadow-md drop-shadow-lg">
                        <DetailsDust></DetailsDust>
                    </ContentsContainer>                    
                    <ContentsContainer className="shadow-md drop-shadow-lg">
                        <DetailsMoon></DetailsMoon>
                    </ContentsContainer>
                </BottomContainer>
            </MainContainer>
        </div>
    );
}

export default DetailsMain;
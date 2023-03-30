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
            <MainContainer className="font-serif drop-shadow-lg bg-gray-100/10">
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
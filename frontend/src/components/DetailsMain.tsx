import React, {useState} from 'react';
import styled from 'styled-components';
import DetailsDate from './DetailsDate';
import DetailsPoint from './DetailsPoint';
import DetailsWeather from './DetailsWeather';
import DetailsHour from './DetailsHour';
import DetailsDust from './DetailsDust';
import DetailsMoon from './DetailsMoon';
import PlaceTitle from './PlaceTitle';
import ContainerButton from './ContainerButton';

function DetailsMain() {

    const EmptySpace = styled.div`
        padding: 21.5%;
    `;

    const MainContainer = styled.div`
        border: 1px solid gray;
        border-radius: 10px;
        padding: 10px;
        margin: 0 auto;
        width: 503px;
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
        margin: 20px;
        height: 100px;
        width: 120px;
    `;


    return (
        <div>
            <EmptySpace></EmptySpace>
            <MainContainer>
                <TopContainer>
                    <img src='../logo.svg'></img>
                    <PlaceTitle></PlaceTitle>
                    <ContainerButton></ContainerButton>
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
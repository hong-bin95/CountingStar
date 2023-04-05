import { useState, useEffect } from "react";
import { GoogleMap, LoadScript } from "@react-google-maps/api";

import styled from "styled-components";
import { SpotType, SpotData } from "../types/SpotType";

import SpotApi from "../apis/SpotApi";

import "../styles/GoogleMain.css";
import GoogleMapStyle from "../styles/GoogleMapStyle";

import Main from "./Main";
import SpotOverlay from "../components/GoogleMain/SpotOverlay";
import PlaceInfo from "../components/GoogleMain/PlaceInfo";
import CustomMarker from "../components/GoogleMain/CustomMarker";
import ToggleButton from "../components/GoogleMain/ToggleButton";
import GradeInfo from "../components/GoogleMain/GradeInfo";

const libraries: (
  | "places"
  | "drawing"
  | "geometry"
  | "localContext"
  | "visualization"
)[] = ["places"];

const mapOptions = {
  styles: GoogleMapStyle,
  fullscreenControl: false,
  mapTypeControl: false,
};

function GoogleMain() {
  // useState 정리
  const [center, setCenter] = useState({ lat: 36.34, lng: 127.77 });
  const [zoom, setZoom] = useState(7.5);
  const [spots, setSpots] = useState<Array<SpotType>>([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [selectedSpot, setSelectedSpot] = useState<SpotType | null>(null); // 마커 클릭 시 선택된 마커 정보를 저장하는 상태 변수
  const [isMainVisible, setIsMainVisible] = useState(true);

  // useEffect 정리
  useEffect(() => {
    getData();
  }, []);

  // 함수 정리
  const getData = async () => {
    const now = new Date();
    const year = now.getFullYear().toString();
    const month = (now.getMonth() + 1).toString().padStart(2, "0");
    const day = now.getDate().toString().padStart(2, "0");

    try {
      const response = await SpotApi().doGetSpot(day, month, year);
      const spotArray = response.data.map((item: SpotData) => ({
        ...item.spot,
        grade: item.grade,
      }));
      setSpots([...spotArray]);
    } catch (error) {
      console.log(error);
    }
  };

  const onLoad = () => {
    setIsLoaded(true);
  };

  // 화면 렌더링
  return (
    <Wrapper>
      <LoadScript
        googleMapsApiKey={`${process.env.REACT_APP_GOOGLE_MAP_KEY}`}
        onLoad={onLoad}
        libraries={libraries}
      >
        {isLoaded && (
          <GoogleMap
            zoom={zoom}
            center={center}
            mapContainerClassName="map-container"
            options={mapOptions}
          >
            {spots.length > 0 &&
              spots.map((spot) => (
                <CustomMarker key={`customMarker-${spot.spotId}`} spot={spot} />
              ))}
            ;
            {spots.length > 0 &&
              spots.map((spot) => (
                <SpotOverlay
                  key={`spotOverlay-${spot.spotId}`}
                  spot={spot}
                  onSpotClick={(selectedSpot) => {
                    setSelectedSpot(selectedSpot);
                    setCenter({
                      lat: parseFloat(selectedSpot.latitude),
                      lng: parseFloat(selectedSpot.longitude),
                    });
                  }}
                />
              ))}
            {selectedSpot !== null && (
              <PlaceInfo
                Spot={selectedSpot}
                onCloseClick={() => setSelectedSpot(null)}
                onUnmount={() => setSelectedSpot(null)}
              ></PlaceInfo>
            )}
          </GoogleMap>
        )}
      </LoadScript>
      <ToggleButton
        isMainVisible={isMainVisible}
        onClick={() => setIsMainVisible(!isMainVisible)}
      />
      <div className={`main-container ${isMainVisible ? "visible" : "hidden"}`}>
        <Main toggleMainVisibility={() => setIsMainVisible(!isMainVisible)} />
      </div>
      <GradeInfo />
    </Wrapper>
  );
}

export default GoogleMain;

const Wrapper = styled.div`
  position: relative;
  .map-container {
    width: 100vw;
    height: 100vh;
  }
`;

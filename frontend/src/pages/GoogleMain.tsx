import { useState, useEffect } from "react";
import { GoogleMap, LoadScript, MarkerF } from "@react-google-maps/api";
import styled from "styled-components";
import MarkerImage from "../assets/Marker.png";
import { SpotType } from "../types/SpotType";
import SpotApi from "../apis/SpotApi";

import Main from "./Main";
import SpotOverlay from "../components/GoogleMain/SpotOverlay";
import PlaceInfo from "../components/GoogleMain/PlaceInfo";
import CustomMarker from "../components/GoogleMain/CustomMarker";

const libraries: (
  | "places"
  | "drawing"
  | "geometry"
  | "localContext"
  | "visualization"
)[] = ["places"];

const mapStyles = [
  {
    featureType: "poi",
    stylers: [
      {
        visibility: "off",
      },
    ],
  },
];

function GoogleMain() {
  // useState 정리
  const [center, setCenter] = useState({ lat: 36.34, lng: 127.77 });
  const [zoom, setZoom] = useState(7.5);
  const [spots, setSpots] = useState<Array<SpotType>>([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [selectedSpot, setSelectedSpot] = useState<SpotType | null>(null); // 마커 클릭 시 선택된 마커 정보를 저장하는 상태 변수

  // useEffect 정리
  useEffect(() => {
    getData();
  }, []);

  // 함수 정리
  const getData = async () => {
    try {
      const response = await SpotApi().doGetSpot();
      setSpots([...response.data]);
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
            options={{ styles: mapStyles }}
          >
            {/* <div
              style={{
                position: "absolute",
                top: "10px",
                left: "10px",
                zIndex: 10,
              }}
            >
              <Main />
            </div> */}
            {spots.length > 0 &&
              spots.map((spot) => (
                <CustomMarker key={spot.spotId} spot={spot} />
              ))}
            ;
            {spots.length > 0 &&
              spots.map((spot) => (
                <SpotOverlay
                  key={spot.spotId}
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

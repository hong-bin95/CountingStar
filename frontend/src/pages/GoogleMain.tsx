import { useState, useEffect } from "react";
import {
  GoogleMap,
  LoadScript,
  MarkerF,
  OverlayView,
  OverlayViewF,
} from "@react-google-maps/api";
import styled from "styled-components";
import MarkerImage from "../assets/Marker.png";
import { MarkerType, SpotType } from "../types/SpotType";
import SpotApi from "../apis/SpotApi";

const libraries: (
  | "places"
  | "drawing"
  | "geometry"
  | "localContext"
  | "visualization"
)[] = ["places"];

function GoogleMain() {
  const center = { lat: 36.34, lng: 127.77 };

  const [spots, setSpots] = useState<Array<SpotType>>([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [selectedMarker, setSelectedMarker] = useState<SpotType | null>(null); // 마커 클릭 시 선택된 마커 정보를 저장하는 상태 변수

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

  useEffect(() => {
    getData();
  }, []);

  return (
    <Wrapper>
      <LoadScript
        googleMapsApiKey={`${process.env.REACT_APP_GOOGLE_MAP_KEY}`}
        onLoad={onLoad}
        libraries={libraries}
      >
        {isLoaded && (
          <GoogleMap
            zoom={7.5}
            center={center}
            mapContainerClassName="map-container"
          >
            {spots.length > 0 &&
              spots.map((spot, index) => (
                <MarkerF
                  key={index}
                  position={{
                    lat: parseFloat(spot.latitude),
                    lng: parseFloat(spot.longitude),
                  }}
                  icon={{
                    url: MarkerImage,
                    scaledSize: new window.google.maps.Size(50, 50),
                  }}
                  onClick={() => setSelectedMarker(spot)}
                />
              ))}

            {spots.length > 0 &&
              spots.map((spot, index) => (
                <OverlayViewF
                  key={index}
                  position={{
                    lat: parseFloat(spot.latitude),
                    lng: parseFloat(spot.longitude),
                  }}
                  mapPaneName={OverlayView.OVERLAY_MOUSE_TARGET}
                  getPixelPositionOffset={(x, y) => ({ x: 0, y: 0 })}
                >
                  <button
                    style={{
                      background: `#203254`,
                      padding: `7px 12px`,
                      fontSize: "11px",
                      color: `white`,
                      borderRadius: "4px",
                    }}
                  >
                    {spot.areaCode}
                  </button>
                </OverlayViewF>
              ))}
          </GoogleMap>
        )}
      </LoadScript>
    </Wrapper>
  );
}

export default GoogleMain;

const Wrapper = styled.div`
  .map-container {
    width: 100vw;
    height: 100vh;
  }
`;

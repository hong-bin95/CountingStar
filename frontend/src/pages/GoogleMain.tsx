import { useState } from "react";
import {
  GoogleMap,
  LoadScript,
  MarkerF,
  OverlayView,
  OverlayViewF,
} from "@react-google-maps/api";
import styled from "styled-components";
import MarkerImage from "../assets/Marker.png";
import { MarkerType } from "../types/MarkerType";

const libraries: (
  | "places"
  | "drawing"
  | "geometry"
  | "localContext"
  | "visualization"
)[] = ["places"];

function GoogleMain() {
  const center = { lat: 36.34, lng: 127.77 };

  const markers: MarkerType[] = [
    { position: { lat: 37.541, lng: 126.986 } },
    { position: { lat: 38.551, lng: 126.986 } },
  ];

  const [isLoaded, setIsLoaded] = useState(false);
  const [selectedMarker, setSelectedMarker] = useState<MarkerType | null>(null); // 마커 클릭 시 선택된 마커 정보를 저장하는 상태 변수

  const onLoad = () => {
    setIsLoaded(true);
  };

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
            {markers.map((marker, index) => (
              <MarkerF
                key={index}
                position={marker.position}
                icon={{
                  url: MarkerImage,
                  scaledSize: new window.google.maps.Size(30, 50),
                }}
                onClick={() => setSelectedMarker(marker)} // 마커 클릭 시 선택된 마커 정보를 저장하는 함수
              />
            ))}

            {markers.map((marker, index) => (
              <OverlayViewF
                key={index}
                position={marker.position}
                mapPaneName={OverlayView.OVERLAY_MOUSE_TARGET}
                getPixelPositionOffset={(x, y) => ({ x: -10, y: -5 })}
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
                  안녕
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

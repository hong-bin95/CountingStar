import { InfoWindowF } from "@react-google-maps/api";
import { useNavigate } from "react-router-dom";
import React from "react";

const PlaceInfo = (Props: any) => {
  const navigate = useNavigate();

  const onCloseClick = () => {
    Props.onCloseClick();
  };

  const onDetailsButtonClick = (spotId: number) => {
    navigate(`/Details/${spotId}`);
  };

  const onUnmount = () => {
    Props.onUnmount();
  };

  return (
    <div className="text-center">
      <InfoWindowF
        position={{
          lat: parseFloat(Props.Spot.latitude),
          lng: parseFloat(Props.Spot.longitude),
        }}
        onCloseClick={onCloseClick}
        // onCloseClick={() => Props.selectedSpot = {null}}
        onUnmount={onUnmount}
      >
        <div>
          <h3>{Props.Spot.spotName}</h3>
          <br></br>
          <button
            className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded inline-flex items-center"
            onClick={() => {
              onDetailsButtonClick(Props.Spot.spotId);
            }}
          >
            상세페이지로 이동
          </button>
        </div>
      </InfoWindowF>
    </div>
  );
};

export default PlaceInfo;

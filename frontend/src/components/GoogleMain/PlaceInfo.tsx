import { InfoWindowF } from "@react-google-maps/api";
import { useNavigate } from "react-router-dom";
import React from "react";

const PlaceInfo = (Props: any) => {
  const navigate = useNavigate();

  const onCloseClick = () => {
    Props.onCloseClick();
  };

  const onDetailsButtonClick = (spotId: number) => {
    navigate(`/detail/${spotId}`);
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
        <div className="bg-white p-4 rounded-lg shadow-md">
          <h3 className="text-lg font-semibold mb-2">{Props.Spot.spotName}</h3>
          <button
            className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded transition duration-200 mt-4 w-full text-center"
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

import { InfoWindowF } from "@react-google-maps/api";
import React from "react";

const PlaceInfo = (Props: any) => {
  const onCloseClick = () => {
    Props.onCloseClick(null);
  };

  return (
    <div>
      <InfoWindowF
        position={{
          lat: parseFloat(Props.Spot.latitude),
          lng: parseFloat(Props.Spot.longitude),
        }}
        onCloseClick={onCloseClick}
        // onCloseClick={() => Props.selectedSpot = {null}}
      >
        <div>
          <h3>{Props.Spot.spotName}</h3>
          <p>{Props.Spot.areaCode}</p>
        </div>
      </InfoWindowF>
    </div>
  );
};

export default PlaceInfo;

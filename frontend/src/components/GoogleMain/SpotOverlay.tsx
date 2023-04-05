import React from "react";
import { OverlayView, OverlayViewF } from "@react-google-maps/api";
import { SpotOverlayType } from "../../types/SpotType";

const SpotOverlay = (props: SpotOverlayType) => {
  const { spot, onSpotClick } = props;

  return (
    <OverlayViewF
      key={spot.spotId}
      position={{
        lat: parseFloat(spot.latitude),
        lng: parseFloat(spot.longitude),
      }}
      mapPaneName={OverlayView.OVERLAY_MOUSE_TARGET}
      getPixelPositionOffset={(x, y) => ({ x: 0, y: 0 })}
    >
      <button
        className="font-serif bg-white text-sky-900 text-sm px-3 py-2 rounded shadow hover:bg-gray-100 border-4 border-black"
        onClick={() => {
          onSpotClick(spot);
        }}
      >
        {spot.spotName}
      </button>
    </OverlayViewF>
  );
};

export default SpotOverlay;

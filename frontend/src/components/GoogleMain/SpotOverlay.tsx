import React from "react";
import { OverlayView, OverlayViewF } from "@react-google-maps/api";
import { SpotType } from "../../types/SpotType";

interface SpotOverlayProps {
  spot: SpotType;
  onSpotClick: (spot: SpotType) => void;
}

const SpotOverlay = (props: SpotOverlayProps) => {
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
        style={{
          background: `#203254`,
          padding: `7px 12px`,
          fontSize: "11px",
          color: `white`,
          borderRadius: "4px",
        }}
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

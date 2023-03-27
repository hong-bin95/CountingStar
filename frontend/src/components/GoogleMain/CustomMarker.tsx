import { MarkerF } from "@react-google-maps/api";
import MarkerImage from "../../assets/Marker.png";
import { useDispatch } from "react-redux";
import { selectSpot } from "../../store/SpotSlice";
import { SpotType } from "../../types/SpotType";

type CustomMarkerProps = {
  spot: SpotType;
};

function CustomMarker({ spot }: CustomMarkerProps) {
  const { latitude, longitude } = spot;
  const dispatch = useDispatch();

  const handleClick = () => {
    dispatch(selectSpot(spot));
  };

  return (
    <MarkerF
      position={{ lat: parseFloat(latitude), lng: parseFloat(longitude) }}
      icon={{
        url: MarkerImage,
        scaledSize: new window.google.maps.Size(50, 50),
      }}
      onClick={handleClick}
    />
  );
}

export default CustomMarker;

import { MarkerF } from "@react-google-maps/api";

import MarkerImage from "../../assets/Marker.png";
import MoonImage from "../../assets/full-moon.png";
import Star_1 from "../../assets/1_star.png";
import Star_2 from "../../assets/2_star.png";
import Star_3 from "../../assets/3_star.png";
import Star_4 from "../../assets/4_star.png";
import Star_5 from "../../assets/5_star.png";

import { useDispatch } from "react-redux";
import { selectSpot } from "../../store/SpotSlice";
import { CustomMarkerType } from "../../types/SpotType";

function CustomMarker({ spot }: CustomMarkerType) {
  const { latitude, longitude } = spot;
  const dispatch = useDispatch();

  const handleClick = () => {
    dispatch(selectSpot(spot));
  };

  const getMarkerImageByGrade = (grade: number) => {
    switch (grade) {
      case 0:
        return MoonImage;
      case 1:
        return Star_1;
      case 2:
        return Star_2;
      case 3:
        return Star_3;
      case 4:
        return Star_4;
      case 5:
        return Star_5;
      default:
        return MarkerImage; // 기본 이미지를 반환합니다.
    }
  };

  return (
    <MarkerF
      position={{ lat: parseFloat(latitude), lng: parseFloat(longitude) }}
      icon={{
        url: getMarkerImageByGrade(spot.grade),
        scaledSize: new window.google.maps.Size(75, 75),
      }}
      onClick={handleClick}
      clickable={false}
    />
  );
}

export default CustomMarker;

export interface MarkerType {
  position: {
    lat: number;
    lng: number;
  };
}

export interface SpotType {
  spotId: number;
  areaCode: string;
  latitude: string;
  longitude: string;
  spotName: string;
  grade: number;
}

export interface CustomMarkerType {
  spot: SpotType;
}

export interface SpotOverlayType {
  spot: SpotType;
  onSpotClick: (spot: SpotType) => void;
}

export interface ToggleButtonType {
  isMainVisible: boolean;
  onClick: () => void;
}

export interface SpotData {
  spot: SpotType;
  grade: number;
}

//SearchBox.tsx
export type searchBoxProps = {
  spotName: string;
  grade: number;
};

//searchMain.tsx
export interface hourToString {
  hourNum: number;
  hourString: string;
}

export type spotData = {
  spotId: number;
  spotName: string;
  latitude: number;
  longtitude: number;
  areaCode: number;
};

export type result = {
  spot: spotData;
  grade: number;
};

//StarPoint.tsx
export type StarPointProps = {
  grade: number;
};

//ThisMonthBox.tsx
export type MonthProps = {
  stellaName: string;
  cnt: number;
};

//ThisMonthMain.tsx
export interface stella {
  constellationId: number;
  constellationName: string;
}

//TodayBox.tsx
export type TodayProps = {
  spotName: string;
  grade: number;
};

//TodayMain.tsx
export interface TodayFucProps {
  toggleMainVisibility: () => void;
}

export interface TodaySpot {
  spotName: string;
  grade: number;
  spotId: number;
}

//weekendBox.tsx
export type weekendBoxProps = {
  spotName: string;
  grade: number;
};

//WeekendMain.tsx
export interface weekendMainSpot {
  spotName: string;
  grade: number;
  spotId: number;
}

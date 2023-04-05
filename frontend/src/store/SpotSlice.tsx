import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { SpotType } from "../types/SpotType";

type SpotState = {
  spots: SpotType[];
  selectedSpot: SpotType | null;
};

const initialState: SpotState = {
  spots: [],
  selectedSpot: null,
};

const spotSlice = createSlice({
  name: "spot",
  initialState,
  reducers: {
    setSpots: (state, action: PayloadAction<SpotType[]>) => {
      state.spots = action.payload;
    },
    selectSpot: (state, action: PayloadAction<SpotType | null>) => {
      state.selectedSpot = action.payload;
    },
  },
});

export const { setSpots, selectSpot } = spotSlice.actions;

export default spotSlice.reducer;

export {};

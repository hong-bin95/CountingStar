import {createSlice, PayloadAction} from '@reduxjs/toolkit';
import { DetailsData } from '../types/DetailsType';

const initialState:DetailsData = {
  day: 0,
  year: '',
  month: '',
  date: '',
  hour: 0,
  spotId: 0,
  spotName: '',
  dust: '',
  weather: '',
};

const DetailsSlice = createSlice({
  name:'DetailsSlice',
  initialState,
  reducers:{
    updateDay:(state, action: PayloadAction<number>)=>{
      state.day = action.payload;
    },
    updateYear:(state, action: PayloadAction<string>)=>{
      state.year = action.payload;
    },
    updateMonth:(state, action: PayloadAction<string>)=>{
      state.month = action.payload;
    },
    updateDate:(state, action: PayloadAction<string>)=>{
      state.date = action.payload;
    },
    updateHour:(state, action: PayloadAction<number>)=>{
      state.hour = action.payload;
    },
    updateSpotId:(state, action: PayloadAction<number>)=>{
      state.spotId = action.payload;
    },
    updateSpotName:(state, action: PayloadAction<string>)=>{
      state.spotName = action.payload;
    },
    updateWeather:(state, action: PayloadAction<string>)=>{
      state.weather = action.payload;
    },
    updateDust:(state, action: PayloadAction<string>)=>{
      state.dust = action.payload;
    },
  },
});
export default DetailsSlice;
export const {updateDay, updateYear, updateMonth, updateDate, updateHour, updateSpotId, updateSpotName, updateWeather, updateDust} = DetailsSlice.actions;
import {createSlice, PayloadAction} from '@reduxjs/toolkit';

export interface DetailsData {
  day:number;
}

const initialState:DetailsData = {
  day: 0,
};

const DetailsSlice = createSlice({
  name:'DetailsSlice',
  initialState,
  reducers:{
    update:(state, action: PayloadAction<number>)=>{
      state.day = action.payload;
    },
  },
});
export default DetailsSlice;
export const {update} = DetailsSlice.actions;
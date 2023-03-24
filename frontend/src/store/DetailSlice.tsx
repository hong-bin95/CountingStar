import {createSlice} from '@reduxjs/toolkit';
const DetailSlice = createSlice({
  name:'DetailSlice',
  initialState:{value:0},
  reducers:{
    up:(state, action)=>{
      state.value = state.value + action.payload;
    }
  }
});
export default DetailSlice;
import {configureStore} from '@reduxjs/toolkit';
import DetailSlice from './DetailSlice';
const store = configureStore({
  reducer:{
    detail:DetailSlice.reducer
  }
});
export default store;
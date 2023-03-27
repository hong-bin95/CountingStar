import {configureStore} from '@reduxjs/toolkit';
import DetailsSlice from './DetailsSlice';
const store = configureStore({
  reducer:{
    DetailsSlice:DetailsSlice.reducer
  }
});
export default store;
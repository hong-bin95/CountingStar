import { configureStore } from "@reduxjs/toolkit";
import DetailsSlice from "./DetailsSlice";
import SpotSlice from "./SpotSlice";

const store = configureStore({
  reducer: {
    DetailsSlice: DetailsSlice.reducer,
    spot: SpotSlice,
  },
});
export default store;
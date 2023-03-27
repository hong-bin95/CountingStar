import { configureStore } from "@reduxjs/toolkit";
import DetailSlice from "./DetailSlice";
import SpotSlice from "./SpotSlice";

const store = configureStore({
  reducer: {
    detail: DetailSlice.reducer,
    spot: SpotSlice,
  },
});
export default store;

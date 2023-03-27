import { configureStore } from "@reduxjs/toolkit";
import DetailSlice from "./DetailSlice";
import MainTodaySlice from "./MainTodaySlice";
import MainWeekendSlice from "./MainWeekendSlice";
const store = configureStore({
  reducer: {
    detail: DetailSlice.reducer,
    mainToday: MainTodaySlice.reducer,
    mainWeekend: MainWeekendSlice.reducer,
  },
});
export default store;

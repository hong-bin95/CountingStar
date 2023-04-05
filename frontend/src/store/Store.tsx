import { configureStore } from "@reduxjs/toolkit";
import DetailsSlice from "./DetailsSlice";
import SpotSlice from "./SpotSlice";
import MainTodaySlice from "./MainTodaySlice";
import MainWeekendSlice from "./MainWeekendSlice";
import MainMonthSlice from "./MainMonthSlice";
const store = configureStore({
  reducer: {
    DetailsSlice: DetailsSlice.reducer,
    spot: SpotSlice,
    mainToday: MainTodaySlice.reducer,
    mainWeekend: MainWeekendSlice.reducer,
    mainThisMonth: MainMonthSlice.reducer,
  },
});
export default store;

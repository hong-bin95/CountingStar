//데이터 가져오는 방식: slice에서 직접 API 통신 및 처리
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

// const getTodayStar = createAsyncThunk(
//   "Maintoday/getTodayStar",

//   async () => {
//     try {
//       const response = await fetch(
//         "https://counting-star.com/api/spot/ranking"
//       );
//       const data = await response.json();
//       console.log(data);
//       return data;
//     } catch (err) {
//       console.log(err);
//     }
//   }
// );

const MainTodaySlice = createSlice({
  name: "Maintoday",
  initialState: {
    baseDateYear: "",
    baseDateMonth: "",
    baseDateHour: "",
    baseDateMinute: "",
    limit: 0,
  },
  reducers: {},
  // extraReducers: (builder) => {
  //   builder.addCase(getTodayStar.fulfilled, (state, action) => {
  //     // (state.status = "complete"),
  //   });
  // },
});

export default MainTodaySlice;

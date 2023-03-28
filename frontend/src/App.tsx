import React from "react";
import { Routes, Route } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store/store";
import Main from "./pages/Main";
import Detail from "./pages/Detail";
import GoogleMain from "./pages/GoogleMain";

function App() {
  return (
    <Provider store={store}>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/detail/:spotId" element={<Detail />} />
        <Route path="/googlemain" element={<GoogleMain />} />
      </Routes>
    </Provider>
  );
}

export default App;

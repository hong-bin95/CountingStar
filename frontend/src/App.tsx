import React from "react";
import { Routes, Route } from "react-router-dom";
import { Provider } from "react-redux";
import Store from "./store/Store";
import Main from "./pages/Main";
import Detail from "./pages/Detail";
import GoogleMain from "./pages/GoogleMain";

function App() {
  return (
    <Provider store={Store}>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/detail" element={<Detail />} />
        <Route path="/googlemain" element={<GoogleMain />} />
      </Routes>
    </Provider>
  );
}

export default App;

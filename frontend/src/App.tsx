import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Routes, Route } from 'react-router-dom';

import Main from './pages/Main';
import Detail from './pages/Detail';
import GoogleMain from './pages/GoogleMain';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Main/>} />
      <Route path="/detail" element={<Detail/>} />
      <Route path="/googlemain" element={<GoogleMain/>} />
    </Routes>
  );
}

export default App;

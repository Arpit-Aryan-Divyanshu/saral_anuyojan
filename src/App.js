import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from "./components/Home";
import SavedQuery from "./components/SavedQuery";
import Interface from "./components/Interface";


function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route exact path="/" element={<Home/>}/>
          <Route exact path="/savedquery" element={<SavedQuery/>}/>
          <Route exact path="/interface" element={<Interface/>}/>
        </Routes>
      </Router>
    </>
  );
}
  
export default App;
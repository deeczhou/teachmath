import React from 'react';
import './App.css';
import CompDrawer from './CompDrawer';
import CompMultiply from './CompMultiply';

function Multiply() {
  return (
    <div className="App">      
      <CompDrawer />
      <CompMultiply />
    </div>
  );
}

export default Multiply;

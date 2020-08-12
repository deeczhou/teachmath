import React from 'react';
import './App.css';
import CompDrawer from './CompDrawer';
import { Typography } from '@material-ui/core';
import CompSubtractions from './CompSubtractions';

function Subtraction() {
  return (
    <div className="App">      
      <CompDrawer />
      <CompSubtractions />
    </div>
  );
}

export default Subtraction;

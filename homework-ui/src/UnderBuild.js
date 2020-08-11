import React from 'react';
import './App.css';
import CompDrawer from './CompDrawer';
import { Typography } from '@material-ui/core';
import CompBuilding from './CompBuilding';

function Building() {
  return (
    <Typography className="App">      
      <CompDrawer />
      <CompBuilding />
    </Typography>
  );
}

export default Building;

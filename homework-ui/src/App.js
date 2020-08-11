import React from 'react';
import './App.css';
import AdditionList from './Additions';
import Drawer from './Drawer';
import { Typography } from '@material-ui/core';

function App() {
  return (
    <Typography className="App">      
      <Drawer />
      <AdditionList />
    </Typography>
  );
}

export default App;

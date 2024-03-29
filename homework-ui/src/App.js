import React from 'react';
import './App.css';
import AdditionList from './CompAdditions';
import CompDrawer from './CompDrawer';
import { Typography } from '@material-ui/core';

function App() {
  return (
    <Typography className="App">      
      <CompDrawer />
      <AdditionList />
    </Typography>
  );
}

export default App;

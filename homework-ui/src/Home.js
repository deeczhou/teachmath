import React from 'react';
import './App.css';
import CompDrawer from './CompDrawer';
import { Typography } from '@material-ui/core';
import CompWelcome from './CompWelcome';

function Home() {
  return (
    <Typography className="App">      
      <CompDrawer />
      <CompWelcome />
    </Typography>
  );
}

export default Home;

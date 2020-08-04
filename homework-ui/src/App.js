import React from 'react';
import logo from './logo.svg';
import axios from 'axios';
import './App.css';
import AdditionList from './apiclient';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          Homework Time <br></br>
          and it is time for Homework~~
        </p>
          Learn Math
      </header>
      
      <AdditionList />
      
    </div>
  );
}

export default App;

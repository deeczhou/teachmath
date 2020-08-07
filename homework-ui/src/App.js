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
          Homework Time
        </p>
      </header>
      
      <AdditionList />
      
    </div>
  );
}

export default App;

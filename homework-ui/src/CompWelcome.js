import React, { useState } from 'react';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import { Input } from '@material-ui/core';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles({
  root: {
    width: '60%',
    textAlign: "center",
    margin:"auto",
  },
});
const state = {};

function handleInputUpperBoundAdd(e) {
  this.setState({upperAdd : e.target.value});
  console.log(this.state);
}



function handleInputUpperBoundMultiply(e) {
  this.setState({upperMulti : e.target.value});
  console.log(this.state);
}

function handleInputLowerBoundMultiply(e) {
  this.setState({lowerMulti : e.target.value});
  console.log(this.state);
}

function handleInputSizeAdd(e) {
  this.setState({sizeAdd : e.target.value});
  console.log(this.state);
}

function handleInputSizeMultiply(e) {
  this.setState({sizeMultiply : e.target.value});
  console.log(this.state);
}


export default function CompWelcome() {
  const classes = useStyles();
  const [lowerAdd, setLowerAdd] = useState(0);
  function handleInputLowerBoundAdd(e) {
    setLowerAdd(e.target.value);
    console.log({lowerAdd});
  }
  
  return (
    <div className={classes.root}>
      <Typography variant="h3" component="h2" gutterBottom>
        Welcome Lads and Gents!
      </Typography>
      <Typography variant="body1" gutterBottom style={{fontSize:"1.7rem"}}>
        Another day, another beginning, another challenge! <br></br>
        Remember! Practice makes perfect! <br></br>
        Giant leaps are made of baby steps~~ <br></br>
      </Typography>
      <br></br>
      
      <Typography>
        <Typography variant="h4" gutterBottom>
          To start, fill in the configs down below:  
        </Typography>
        <br></br>
          
        <Typography  variant="h6" noWrap>
          Lower Bound for add and substract:
          <br></br>
          
          <Input variant="h7" 
            type="tel"
            onChange={(e) => handleInputLowerBoundAdd(e)}
            className="App-input"
            multiline={false}
            required={true}
            />
          <Input variant="h7" type="tel"
            onChange={(e) => handleInputUpperBoundAdd(e)}
            className="App-input"
            multiline={false}
            required={true}
            />
          </Typography>
          <br></br>
          <Typography variant="h6" noWrap>
            Number Range for multiplication and division
            <br></br>
            <Input variant="h7" 
              type="tel"
              onChange={(e) => handleInputLowerBoundMultiply(e)}
              className="App-input"
              multiline={false}
              required={true}
            />
            <Input variant="h7" type="tel"
              onChange={(e) => handleInputUpperBoundMultiply(e)}
              className="App-input"
              multiline={false}
              required={true}
            />
          </Typography>
          <br></br>
          <br></br>
          <Typography variant="h6" noWrap>
            Number of questions:
            <br></br>
            <br></br>
            <Typography>
              Addition and Subtraction:
              <Input variant="h8" type="tel"
                onChange={(e) => handleInputSizeAdd(e)}
                className="App-input"
                multiline={false}
                required={true}
              />
            </Typography>
            <br></br>
            <Typography>
              Multiplication and Divisions:
              <Input variant="h8" type="tel"
                onChange={(e) => handleInputSizeMultiply(e)}
                className="App-input"
                multiline={false}
                required={true}
              />
            </Typography>
          </Typography>
          <br></br>
          <Button variant="contained" color="primary" size="medium" onClick= {() =>{alert("Configuration Saved!")}} type="success">Save</Button>
        </Typography>
    </div>
  );
}

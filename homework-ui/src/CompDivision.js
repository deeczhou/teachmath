import React from 'react';
import axios from 'axios';
import { Card, Box } from '@material-ui/core'
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import Input from '@material-ui/core/Input';
import { makeStyles } from '@material-ui/core/styles';
import { withStyles } from '@material-ui/core/styles';
import Timer from './CompTimer';

const useStyles = makeStyles((theme) => ({
  root: {
    '& .MuiInputBase-input': {
      fontSize: '2rem',
    },
  },
}));

class CompDivision extends React.Component {
  state = {
    questions: [],
    answers: [],
    correct: 0,
    wrong: 0,
    colors: [],
    submitTimes: 0,
  }
  
  incrementSubmits() {
    var submits = this.state.submitTimes;
    submits++;
    this.setState({submitTimes:submits});
  }

  check() {
    this.incrementSubmits();
    var Qs = this.state.questions;
    var As = this.state.answers;
    var Cs = this.state.colors;
    var correct = 0;
    var wrong = 0;
    
    Qs.forEach((question, i) => {
      if (As[i] != null){
        console.log(As[i]);
        console.log(question)
        if(parseInt(As[i]) === question.answer) {
          correct++;
          Cs[i] = 'green';
        } else {
          wrong++;
          Cs[i] = 'red';
        }
      } else {
        wrong++;
        Cs[i] = 'red';
      }
    });
    console.log(correct);
    console.log(wrong);
    this.setState({correct : correct});
    this.setState({wrong : wrong});
    this.setState({colors: Cs});
  }

  handleInput(e, i) {
    var ansArray = this.state.answers;
    ansArray[i] = e.target.value
    this.setState({answers : ansArray});
    console.log(this.state);
  }

  getQuestions() {
    var lower = this.state.lower;
    var size = this.state.size;
    var submitButton = document.getElementById("submitButton")
    console.log(lower);
    console.log(size);
    axios.get(`http://209.121.54.188:18200/division?denominator=${lower}&size=${size}`)
      .then(res => {
        const questions = res.data.questions;
        this.setState({ questions });
        console.log(questions);
      }).catch(err => {
        this.trylocalUrl(lower, size);
        console.log(err);
      })
    
    submitButton.style.display = "block";
    document.getElementById("inputBlock").style.display = "none";
  }

  trylocalUrl(lower, size) {
    axios.get(`http://209.121.54.188:8989/division?denominator=${lower}&size=${size}`)
    .then(res => {
      const questions = res.data.questions;
      this.setState({ questions });
      console.log(questions);
    }).catch(err => {
      console.log(err);
    })
  }

  render() {
    return (
      <Typography>
      <Box>
        <Typography variant="h4">Divisions</Typography>
        <Typography>
          <Timer />
        </Typography>
        {this.state.questions.map((q, i) => 
          <Card key={i} style={{color: this.state.colors[i]}}>
            <CardContent>
              <Typography variant="h5" component="h2" >
                Q.{i+1}:  {q.numerator} / {q.denominator} =  
                  <Input
                    type="tel"
                    onChange={(e) => this.handleInput(e, i)}
                    style={{fontSize:'1.7rem'}}
                    className="App-input"
                  />
              </Typography>
            </CardContent>
          </Card>
        )}
        <Typography id="submitButton" style={{display:"none"}}>
          <IconButton size="medium" onClick={()=> this.check()}>Submit</IconButton>
          <br></br>
          Correct Answers: {this.state.correct}. Wrong Answers: {this.state.wrong}, Attempts: {this.state.submitTimes}
        </Typography>
      </Box>
      <Typography id="inputBlock">
            <Typography variant="h6" noWrap>
              Division Configurations
            </Typography>
            <Typography variant="h8" noWrap>
              Largest Denominator:
              <Input variant="h8" 
                type="tel"
                onChange={(e) => this.handleInputLowerBound(e)}
                className="App-input"
                multiline={false}
                required={true}
              />
            </Typography>
              <br></br>
            <Typography variant="h8" noWrap>
              Number of questions:
                <Input variant="h8" type="tel"
                onChange={(e) => this.handleInputSize(e)}
                className="App-input"
                multiline={false}
                required={true}
              />  
            </Typography>  
              <br></br>  
            <IconButton size="medium" onClick={()=> this.getQuestions()} type="success">Generate</IconButton>
        </Typography>
      </Typography>
    );
  }

  handleInputLowerBound(e) {
    this.setState({lower: e.target.value});
    console.log(this.state);
  }

  handleInputSize(e) {
    this.setState({size : e.target.value});
    console.log(this.state);
  }

}

export default withStyles(useStyles)(CompDivision)
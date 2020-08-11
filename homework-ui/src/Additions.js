import React from 'react';
import axios from 'axios';
import { Card } from '@material-ui/core'
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import Input from '@material-ui/core/Input';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  root: {
    '& .MuiInputBase-input': {
      fontSize: '2rem',
    },
  },
}));

class AdditionList extends React.Component {
  state = {
    questions: [],
    answers: [],
    correct: 0,
    wrong: 0,
    colors: []
  }
  
  check() {
    console.log(this.state.answers);
    console.log(this.state.questions);

    var Qs = this.state.questions;
    var As = this.state.answers;
    var Cs = this.state.colors;
    var correct = 0;
    var wrong = 0;
    Qs.forEach((question, i) => {
      if (As[i] != null){
        console.log(As[i]);
        console.log(question)
        if(parseInt(As[i]) === question.sum) {
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
    var upper = this.state.upper;
    var size = this.state.size;
    var submitButton = document.getElementById("submitButton")
    console.log(lower);
    console.log(upper);
    console.log(size);
    axios.get(`http://chips4ever.duckdns.org:18200/add?from=${lower}&to=${upper}&size=${size}`)
      .then(res => {
        const questions = res.data.questions;
        this.setState({ questions });
        console.log(questions);
      }).catch(err => {
        this.trylocalUrl(lower, upper, size);
        console.log(err);
      })
    
    submitButton.style.display = "block";
    document.getElementById("inputBlock").style.display = "none";
  }

  trylocalUrl(lower, upper, size) {
    axios.get(`http://chips4ever.duckdns.org:8989/add?from=${lower}&to=${upper}&size=${size}`)
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
        <h1>Additions</h1>
        {this.state.questions.map((q, i) =>
          <Card key={i} style={{color: this.state.colors[i]}}>
            <CardContent>
              <Typography variant="h4" component="h2" >
                Q.{i+1}:  {q.a} + {q.b} =
                  <Input
                    type="tel"
                    onChange={(e) => this.handleInput(e, i)}
                    style={{fontSize:'2rem'}}
                    className="App-Input"
                  />
              </Typography>
              <Typography variant="h4" component="h2" hidden={true} id="hiddenAnswer">
                answer = {q.sum}
              </Typography>
            </CardContent>
          </Card>
        )}
        <Typography id="submitButton" style={{display:"none"}}>
          <IconButton size="medium" onClick={()=> this.check()}>Submit</IconButton>
          <br></br>
          Correct Answers: {this.state.correct}. Wrong Answers: {this.state.wrong}
        </Typography>

        <Typography id="inputBlock">
            <Typography variant="h6" noWrap>
              Addition Configurations
            </Typography>
            <Typography variant="h8" noWrap>
              Smallest Number:
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
              Largest Number: 
              <Input variant="h8" type="tel"
                onChange={(e) => this.handleInputUpperBound(e)}
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

  handleInputUpperBound(e) {
    this.setState({upper : e.target.value});
    console.log(this.state);
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

export default withStyles(useStyles)(AdditionList)
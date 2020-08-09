import React from 'react';
import axios from 'axios';
import { Card } from '@material-ui/core'
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import InputBase from '@material-ui/core/InputBase';
import IconButton from '@material-ui/core/IconButton';

export default class AdditionList extends React.Component {
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

  componentDidMount() {
    axios.get(`http://chips4ever.duckdns.org:18200/add?from=100&to=1000&size=20`)
      .then(res => {
        const questions = res.data.questions;
        this.setState({ questions });
        console.log(questions);
      }).catch(err => {
        this.trylocalUrl();
        console.log(err);
      })
  }

  trylocalUrl() {
    axios.get(`http://chips4ever.duckdns.org:8989/add?from=100&to=1000&size=20`)
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
      <div>
        <h1>Additions</h1>
        {this.state.questions.map((q, i) => 
          <Card key={i} style={{color: this.state.colors[i]}}>
            <CardContent>
              <Typography variant="h4" component="h2" >
                <div>Q.{i+1}:  {q.a} + {q.b} =    
                  <input 
                    type="number"
                    onChange={(e) => this.handleInput(e, i)}
                    className="Add-Inputbox"
                  />
                </div>
              </Typography>
              <Typography variant="h4" component="h2" hidden={true} id="hiddenAnswer">
                answer = {q.sum} 
              </Typography>
            </CardContent>
          </Card>
        )}
        <IconButton size="medium" onClick={()=> this.check()} type="success">Submit</IconButton>
        <p>Correct Answers: {this.state.correct}. Wrong Answers: {this.state.wrong}</p>
      </div>
    );
  }
}